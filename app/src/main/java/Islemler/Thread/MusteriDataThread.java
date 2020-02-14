package Islemler.Thread;

import android.content.Context;
import android.view.Display;
import android.widget.Button;

import Islemler.FirebaseBoolean;
import Islemler.HideShow.ButtonsHideShow;

public class MusteriDataThread extends ToastLoad {
    public MusteriDataThread(Context context, Display display){
        super(context, display);
    }

    public void firebaseThread(Button button[]) //firebaseden gelen verilerin gecikmesini kontrol ediyor
    {
        final ButtonsHideShow hideShow = new ButtonsHideShow();
        final Button buttonlar[]=button;

        hideShow.buttonlariGizle(buttonlar);
        this.showLt("Veriler çekiliyor");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(
                        FirebaseBoolean.getMenuFonksiyonaGirisKontrol()
                        ||
                        FirebaseBoolean.getMuzikFonksiyonaGirisKontrol()
                ){
                    try{
                        Thread.sleep(500);

                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideLt();
                        hideShow.buttonlariGoster(buttonlar);
                    }
                });
            }
        });
        thread.start();

    }
}
