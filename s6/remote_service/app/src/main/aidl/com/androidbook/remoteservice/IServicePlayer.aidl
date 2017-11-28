package com.androidbook.remoteservice;
interface IServicePlayer{
	void play(); //����
	void pause(); //��ͣ
	void stop(); //ֹͣ
	int getDuration(); //ʱ��
	int getCurrentPosition(); //��ǰλ��
	void seekTo(int current); //�϶�λ��
	boolean setLoop(boolean loop); //�Ƿ�ѭ������
}