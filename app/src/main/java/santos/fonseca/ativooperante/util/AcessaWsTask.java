package santos.fonseca.ativooperante.util;

import android.os.AsyncTask;

public class AcessaWsTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {

        String dados = AcessaWS.consumir(strings[0]);
        return dados;
    }
}
