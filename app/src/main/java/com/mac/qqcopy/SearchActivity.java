package com.mac.qqcopy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mac on 2016/11/3.
 */

public class SearchActivity extends Activity implements View.OnClickListener, TextWatcher{

    private TextView tv_origin;
    private Button btn_cancel;
    private EditText et_search;
    private ImageButton ibtn_delete;
    //ListView list_result = (ListView) findViewById(R.id.list_result);  //為了控制是否顯示，將其變成欄位> but不能在這裡findid
    private ListView list_result;
    private String[] names = new String[]{"劉曉鳴","張芳瑜","簡量心"};
    private String[] says = new String[]{"剛剛發檔案給你囉","今天是我的天中了100塊","我跟你說一個小秘密～～～"};
    private int[] imgIds = new int[]{R.drawable.crown,R.drawable.color_palette,R.drawable.id_card};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent it = getIntent(); //另一個class傳過來這裡也要接收;

        //上方的搜尋bar
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        et_search = (EditText) findViewById(R.id.et_search);
        et_search.addTextChangedListener(this);  //監聽text輸入

        ibtn_delete = (ImageButton) findViewById(R.id.ibtn_delete);
        ibtn_delete.setOnClickListener(this);

        //下方搜尋提示，按了會回到主頁
        tv_origin = (TextView) findViewById(R.id.tv_origin);  //要點擊前要先設定監聽事件
        tv_origin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                    backToMainPage();
                break;
            case R.id.tv_origin:
                backToMainPage();
                break;
            case R.id.ibtn_delete:
                deleteText();
                break;
            default:
                break;
        }
    }

    private void deleteText() {
        et_search.setText("");
    }

    private void backToMainPage() {
        Intent it = new Intent(this, MyFragmentActivity.class);
        this.startActivity(it);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable e) {  //判斷是否有輸入，如有就會看到delete鈕
        setSearchResult();
        if(e.length() != 0){
            ibtn_delete.setVisibility(View.VISIBLE);
            tv_origin.setVisibility(View.INVISIBLE);
            list_result.setVisibility(View.VISIBLE);
        }else {
            ibtn_delete.setVisibility(View.INVISIBLE);
            tv_origin.setVisibility(View.VISIBLE);
            list_result.setVisibility(View.INVISIBLE);
        }
        String result = et_search.getText().toString();   //String str_search = (String)et_search.getText();不能強制轉型
    }

    public void setSearchResult(){
        //放入搜尋結果的list
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("touxiang", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }

        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.search_list,
                new String[]{"touxiang", "name", "says"}, new int[]{R.id.imgtou, R.id.name, R.id.says});
        list_result = (ListView) findViewById(R.id.list_result);
        list_result.setAdapter(myAdapter);
    }

}
























