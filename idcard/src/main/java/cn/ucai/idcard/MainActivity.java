package cn.ucai.idcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mName, mId, mNational, mAddress;
    TextView mBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.et_name);
        mAddress = (EditText) findViewById(R.id.et_address);
        mNational = (EditText) findViewById(R.id.et_national);
        mId = (EditText) findViewById(R.id.et_id);
        mBirthday = (TextView) findViewById(R.id.birthday);
    }

    public void save(View view) {
        if (checkinput()) {
            String year = mId.getText().toString().substring(6, 10);
            String month = mId.getText().toString().substring(10, 12);
            String day = mId.getText().toString().substring(12, 14);
            mBirthday.setText(year + "年" + month + "月" + day + "日");
            Toast.makeText(this, mName.getText().toString() + "\n保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkinput() {
        String id = mId.getText().toString().trim();
        if (TextUtils.isEmpty(mAddress.getText().toString())) {
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mName.getText().toString())) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mId.getText().toString())) {
            Toast.makeText(this, "身份证不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mNational.getText().toString())) {
            Toast.makeText(this, "民族不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!id.matches("[\\d]{18}")) {
            Toast.makeText(this, "身份证号要18位", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
