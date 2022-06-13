package santos.fonseca.ativooperante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import santos.fonseca.ativooperante.entidades.Denuncia;
import santos.fonseca.ativooperante.entidades.Orgao;
import santos.fonseca.ativooperante.entidades.Tipo;
import santos.fonseca.ativooperante.entidades.Login;
import santos.fonseca.ativooperante.util.ResultService;

public class Formulario extends AppCompatActivity {

    private EditText etTitulo, etDescricao;
    private RatingBar rbUrgencia;
    private Spinner spOrgao, spTipo;
    private FloatingActionButton btSave;
    private String loginAtual, senhadoLogin;
    private List<Orgao> orgaos;
    private List<Tipo> tipos;
    private List<String> tiposN, orgaosN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        loginAtual = message.split(";")[0];
        senhadoLogin = message.split(";")[1];
        initialize();
    }

    private void limpaCampos() {

        etTitulo.setText("");
        etDescricao.setText("");
    }

    private void initialize() {

        initializeListas();
        ArrayAdapter<String> adapterO = null;
        ArrayAdapter<String> adapterT = null;

        etTitulo = findViewById(R.id.etTitulo);
        rbUrgencia = findViewById(R.id.rbUrgencia);

        spOrgao = findViewById(R.id.spOrgao);
        adapterO = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, orgaosN);
        adapterO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrgao.setAdapter(adapterO);

        spTipo = findViewById(R.id.spTipo);
        adapterT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tiposN);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adapterT);

        etDescricao = findViewById(R.id.etDescricao);
        btSave = findViewById(R.id.fbSave);

        btSave.setOnClickListener(e -> {
            try {
                save();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initializeListas() {

        orgaos = ResultService.listaOrgaos();
        tipos = ResultService.listaTipos();

        tiposN = new ArrayList<>();
        for(Tipo t: tipos)
            tiposN.add(t.getNome());
        orgaosN = new ArrayList<>();
        for(Orgao o: orgaos)
            orgaosN.add(o.getNome());
    }

    private void save() throws ExecutionException, InterruptedException {

        Toast toast;
        Orgao o = orgaos.get(spOrgao.getSelectedItemPosition());
        Tipo t = tipos.get(spTipo.getSelectedItemPosition());
        Login l = new Login("",loginAtual,senhadoLogin);
        Denuncia d = new Denuncia(rbUrgencia.getNumStars(),etTitulo.getText().toString(),etDescricao.getText().toString(),
                o,t,ResultService.validaLoginAPI(l));

        if (ResultService.enviaDenunciaAPI(d))
            toast = Toast.makeText(getApplicationContext(), "Denúncia Enviada!!", Toast.LENGTH_LONG);
        else
            toast = Toast.makeText(getApplicationContext(), "Erro ao enviar!!", Toast.LENGTH_LONG);
        toast.show();
        limpaCampos();
    }

    public void onClick_FormText(View view) {

        if(etTitulo.getText().toString().equals("Título") && etTitulo.isSelected())
            etTitulo.setText("");
        if(etDescricao.getText().toString().equals("Descrição do Problema") && etDescricao.isSelected())
            etDescricao.setText("");
    }
}