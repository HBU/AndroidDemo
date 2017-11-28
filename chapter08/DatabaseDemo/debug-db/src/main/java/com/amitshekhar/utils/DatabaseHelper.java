/*
 *
 *  *    Copyright (C) 2016 Amit Shekhar
 *  *    Copyright (C) 2011 Android Open Source Project
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.amitshekhar.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amitshekhar.model.Response;
import com.amitshekhar.model.RowDataRequest;
import com.amitshekhar.model.TableDataResponse;
import com.amitshekhar.model.UpdateRowResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitshekhar on 06/02/17.
 */

public class DatabaseHelper {

    private DatabaseHelper() {
        // This class in not publicly instantiable
    }

    public static Response getAllTableName(SQLiteDatabase database) {
        Response response = new Response();
        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                response.rows.add(c.getString(0));
                c.moveToNext();
            }
        }
        c.close();
        response.isSuccessful = true;
        try {
            response.dbVersion = database.getVersion();
        } catch (Exception ignore) {

        }
        return response;
    }

    public static TableDataResponse getTableData(SQLiteDatabase db, String selectQuery, String tableName) {

        TableDataResponse tableData = new TableDataResponse();
        tableData.isSelectQuery = true;
        if (tableName == null) {
            tableName = getTableName(selectQuery);
        }

        if (tableName != null) {
            final String pragmaQuery = "PRAGMA table_info(" + tableName + ")";
            tableData.tableInfos = getTableInfo(db, pragmaQuery);
        }

        tableData.isEditable = tableName != null && tableData.tableInfos != null;

        Cursor cursor;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            e.printStackTrace();
            tableData.isSuccessful = false;
            tableData.errorMessage = e.getMessage();
            return tableData;
        }

        if (cursor != null) {
            cursor.moveToFirst();

            // setting tableInfo when tableName is not known and making
            // it non-editable also by making isPrimary true for all
            if (tableData.tableInfos == null) {
                tableData.tableInfos = new ArrayList<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    TableDataResponse.TableInfo tableInfo = new TableDataResponse.TableInfo();
                    tableInfo.title = cursor.getColumnName(i);
                    tableInfo.isPrimary = true;
                    tableData.tableInfos.add(tableInfo);
                }
            }

            tableData.isSuccessful = true;
            tableData.rows = new ArrayList<>();
            if (cursor.getCount() > 0) {

                do {
                    List<TableDataResponse.ColumnData> row = new ArrayList<>();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        TableDataResponse.ColumnData columnData = new TableDataResponse.ColumnData();
                        switch (cursor.getType(i)) {
                            case Cursor.FIELD_TYPE_BLOB:
                                columnData.dataType = DataType.TEXT;
                                columnData.value = ConverterUtils.blobToString(cursor.getBlob(i));
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                columnData.dataType = DataType.REAL;
                                columnData.value = cursor.getDouble(i);
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                columnData.dataType = DataType.INTEGER;
                                columnData.value = cursor.getLong(i);
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                columnData.dataType = DataType.TEXT;
                                columnData.value = cursor.getString(i);
                                break;
                            default:
                                columnData.dataType = DataType.TEXT;
                                columnData.value = cursor.getString(i);
                        }
                        row.add(columnData);
                    }
                    tableData.rows.add(row);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return tableData;
        } else {
            tableData.isSuccessful = false;
            tableData.errorMessage = "Cursor is null";
            return tableData;
        }

    }

    private static List<TableDataResponse.TableInfo> getTableInfo(SQLiteDatabase db, String pragmaQuery) {

        Cursor cursor;
        try {
            cursor = db.rawQuery(pragmaQuery, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (cursor != null) {

            List<TableDataResponse.TableInfo> tableInfoList = new ArrayList<>();

            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                do {
                    TableDataResponse.TableInfo tableInfo = new TableDataResponse.TableInfo();

                    for (int i = 0; i < cursor.getColumnCount(); i++) {

                        final String columnName = cursor.getColumnName(i);

                        switch (columnName) {
                            case Constants.PK:
                                tableInfo.isPrimary = cursor.getInt(i) == 1;
                                break;
                            case Constants.NAME:
                                tableInfo.title = cursor.getString(i);
                                break;
                            default:
                        }

                    }
                    tableInfoList.add(tableInfo);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return tableInfoList;
        }
        return null;
    }

    public static UpdateRowResponse updateRow(SQLiteDatabase db, String tableName, List<RowDataRequest> rowDataRequests) {

        UpdateRowResponse updateRowResponse = new UpdateRowResponse();

        if (rowDataRequests == null || tableName == null) {
            updateRowResponse.isSuccessful = false;
            return updateRowResponse;
        }

        ContentValues contentValues = new ContentValues();

        String whereClause = null;
        List<String> whereArgsList = new ArrayList<>();

        for (RowDataRequest rowDataRequest : rowDataRequests) {
            if (Constants.NULL.equals(rowDataRequest.value)) {
                rowDataRequest.value = null;
            }
            if (rowDataRequest.isPrimary) {
                if (whereClause == null) {
                    whereClause = rowDataRequest.title + "=? ";
                } else {
                    whereClause = whereClause + "and " + rowDataRequest.title + "=? ";
                }
                whereArgsList.add(rowDataRequest.value);
            } else {
                switch (rowDataRequest.dataType) {
                    case DataType.INTEGER:
                        contentValues.put(rowDataRequest.title, Long.valueOf(rowDataRequest.value));
                        break;
                    case DataType.REAL:
                        contentValues.put(rowDataRequest.title, Double.valueOf(rowDataRequest.value));
                        break;
                    case DataType.TEXT:
                        contentValues.put(rowDataRequest.title, rowDataRequest.value);
                        break;
                    default:
                }
            }
        }

        String[] whereArgs = new String[whereArgsList.size()];

        for (int i = 0; i < whereArgsList.size(); i++) {
            whereArgs[i] = whereArgsList.get(i);
        }

        db.update(tableName, contentValues, whereClause, whereArgs);
        updateRowResponse.isSuccessful = true;
        return updateRowResponse;
    }


    public static UpdateRowResponse deleteRow(SQLiteDatabase db, String tableName, List<RowDataRequest> rowDataRequests) {

        UpdateRowResponse updateRowResponse = new UpdateRowResponse();

        if (rowDataRequests == null || tableName == null) {
            updateRowResponse.isSuccessful = false;
            return updateRowResponse;
        }


        String whereClause = null;
        List<String> whereArgsList = new ArrayList<>();

        for (RowDataRequest rowDataRequest : rowDataRequests) {
            if (Constants.NULL.equals(rowDataRequest.value)) {
                rowDataRequest.value = null;
            }
            if (rowDataRequest.isPrimary) {
                if (whereClause == null) {
                    whereClause = rowDataRequest.title + "=? ";
                } else {
                    whereClause = whereClause + "and " + rowDataRequest.title + "=? ";
                }
                whereArgsList.add(rowDataRequest.value);
            }
        }

        if (whereArgsList.size() == 0) {
            updateRowResponse.isSuccessful = true;
            return updateRowResponse;
        }

        String[] whereArgs = new String[whereArgsList.size()];

        for (int i = 0; i < whereArgsList.size(); i++) {
            whereArgs[i] = whereArgsList.get(i);
        }

        db.delete(tableName, whereClause, whereArgs);
        updateRowResponse.isSuccessful = true;
        return updateRowResponse;
    }


    public static TableDataResponse exec(SQLiteDatabase database, String sql) {
        TableDataResponse tableDataResponse = new TableDataResponse();
        tableDataResponse.isSelectQuery = false;
        try {
            database.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            tableDataResponse.isSuccessful = false;
            tableDataResponse.errorMessage = e.getMessage();
            return tableDataResponse;
        }
        tableDataResponse.isSuccessful = true;
        return tableDataResponse;
    }

    private static String getTableName(String selectQuery) {
        // TODO find tableName from query and also handle JOIN query
        return null;
    }

}
