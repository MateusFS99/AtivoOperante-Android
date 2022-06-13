package santos.fonseca.ativooperante;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import santos.fonseca.ativooperante.entidades.Login;
import santos.fonseca.ativooperante.ui.main.SectionsPagerAdapter;
import santos.fonseca.ativooperante.util.ResultService;

public class MainActivity extends AppCompatActivity {

    private EditText etEmailL, etSenhaL, etCpf, etEmailR, etSenhaR, etSenhaCR;
    private Button btLogin, btRegistro;
    public static final String EXTRA_MESSAGE = "santos.fonseca.ativooperante.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void initialize() {

        etEmailL = findViewById(R.id.etEmailL);
        etSenhaL = findViewById(R.id.etSenhaL);
        btLogin = findViewById(R.id.btLogin);

        etCpf = findViewById(R.id.etCpf);
        etEmailR = findViewById(R.id.etEmailR);
        etSenhaR = findViewById(R.id.etSenhaR);
        etSenhaCR = findViewById(R.id.etSenhaCR);
        btRegistro = findViewById(R.id.btRegistro);
    }

    private void limpaCampos() {

        etEmailL.setText("");
        etSenhaL.setText("");
        etCpf.setText("");
        etEmailR.setText("");
        etSenhaR.setText("");
        etSenhaCR.setText("");
    }

    private boolean validaLogin() {

        Login l = new Login("", etEmailL.getText().toString(), etSenhaL.getText().toString());
        Login resul = ResultService.validaLoginAPI(l);
        if(resul.getEmail().equals(l.getEmail()))
            return true;
        else {

            Toast toast = Toast.makeText(getApplicationContext(), "E-Mail/Senha Inválidos", Toast.LENGTH_SHORT);
            toast.show();

            return false;
        }
    }

    private boolean validaRegistro() {

        if(etSenhaR.getText().toString().equals(etSenhaCR.getText().toString())) {

            Login l = new Login(etCpf.getText().toString(),etEmailR.getText().toString(),etSenhaCR.getText().toString());

            ResultService.enviaRegistroAPI(l);

            return true;
        }
        else {

            Toast toast = Toast.makeText(getApplicationContext(), "Senhas Não Iguais", Toast.LENGTH_SHORT);
            toast.show();

            return false;
        }
    }

    public void onClick_Login(View view) {

        initialize();
        if (validaLogin()) {

            Toast toast = Toast.makeText(getApplicationContext(), "Bem Vindo!!", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, Formulario.class);
            intent.putExtra(EXTRA_MESSAGE, etEmailL.getText().toString() + ";" + etSenhaL.getText().toString());
            limpaCampos();
            startActivity(intent);
        }
    }

    public void onClick_Registro(View view) {

        initialize();
        if (validaRegistro()) {

            Toast toast = Toast.makeText(getApplicationContext(), "Cadastro novo? Bem Vindo!!", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, Formulario.class);
            intent.putExtra(EXTRA_MESSAGE, etEmailR.getText().toString() + ";" + etSenhaCR.getText().toString());
            limpaCampos();
            startActivity(intent);
        }
    }
}