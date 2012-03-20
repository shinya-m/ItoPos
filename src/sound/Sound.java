/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sound;

/**
 *
 * @author Administrator
 */
//SoundClass.java
//javax.sound.sampledインターフェイスは、JDK 1.3以降のサポートです
import java.io.*;
import java.util.concurrent.Callable;
import javax.sound.sampled.*;

public class Sound implements Callable
{
    private static final int EXTERNAL_BUFFER_SIZE = 128000;
    private String fileName;

    public void PlayWave(String fileName)
    {
        try
        {
            // Fileクラスのインスタンスを生成
            File soundFile = new File(fileName);
            // オーディオ入力ストリームを取得します
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            // オーディオ形式を取得します
            AudioFormat audioFormat = audioInputStream.getFormat();

            // データラインの情報オブジェクトを生成します
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
            // 指定されたデータライン情報に一致するラインを取得します
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            // 指定されたオーディオ形式でラインを開きます
            line.open(audioFormat);
            // ラインでのデータ入出力を可能にします
            line.start();

            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            while (nBytesRead != -1) {
                // オーディオストリームからデータを読み込みます
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    // オーディオデータをミキサーに書き込みます
                    int nBytesWritten = line.write(abData, 0, nBytesRead);
                }
            }
            // ラインからキューに入っているデータを排出します
            line.drain();
            // ラインを閉じます
            line.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object call() throws Exception {
        PlayWave(fileName);
        return true;
    }

    public Sound(String fileName) {
        this.fileName = fileName;
    }



}

