package santos.fonseca.ativooperante.util;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import santos.fonseca.ativooperante.entidades.Denuncia;
import santos.fonseca.ativooperante.entidades.Login;
import santos.fonseca.ativooperante.entidades.Orgao;
import santos.fonseca.ativooperante.entidades.Tipo;

public class ResultService {

    public static Login validaLoginAPI(Login l) {

        List<Login> logins;
        AcessaWsTask task = new AcessaWsTask();
        Gson gson = new GsonBuilder().create();
        String json = null;
        try {
            json = task.execute("http://192.168.0.104:8080/validalogin?email=" + l.getEmail() + "&senha=" + l.getSenha()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        java.lang.reflect.Type listType = new TypeToken<ArrayList<Login>>(){}.getType();
        logins = (List<Login>)gson.fromJson(json, listType);

        return logins.get(0);
    }

    public static void enviaRegistroAPI(Login l) {

        AcessaWsTask task = new AcessaWsTask();

        try {
            task.execute("http://192.168.0.104:8080/receberregistro" +
                    "?cpf=" + l.getCpf() + "&email=" + l.getEmail() + "&senha=" + l.getSenha()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<Orgao> listaOrgaos() {

        List<Orgao> orgaos;
        AcessaWsTask task = new AcessaWsTask();
        Gson gson = new GsonBuilder().create();
        String json = null;

        try {
            json = task.execute("http://192.168.0.104:8080/todosorgaos").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        java.lang.reflect.Type listType = new TypeToken<ArrayList<Orgao>>(){}.getType();
        orgaos = (List<Orgao>)gson.fromJson(json, listType );

        return orgaos;
    }

    public static List<Tipo> listaTipos() {

        List<Tipo> tipos;
        List<String> nomes = new ArrayList<>();
        AcessaWsTask task = new AcessaWsTask();
        Gson gson = new GsonBuilder().create();
        String json = null;

        try {
            json = task.execute("http://192.168.0.104:8080/todostipos").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        java.lang.reflect.Type listType = new TypeToken<ArrayList<Tipo>>(){}.getType();
        tipos = (List<Tipo>)gson.fromJson(json, listType);
        for(Tipo t: tipos)
            nomes.add(t.getNome());

        return tipos;
    }

    public static boolean enviaDenunciaAPI(Denuncia d) {

        AcessaWsTask task = new AcessaWsTask();

        try {
            task.execute("http://192.168.0.104:8080/receberdenuncia" +
                    "?titulo=" + d.getTitulo() + "&descricao=" + d.getDescricao() +
                    "&urgencia=" + d.getUrgencia() + "&orgao=" + d.getOrgao().getId() +
                    "&tipo=" + d.getTipo().getId() + "&login=" + d.getLogin().getId()).get();

            return true;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}