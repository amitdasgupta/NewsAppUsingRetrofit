package com.example.amitdasgupta.newsappusingretrofit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dd.morphingbutton.MorphingButton;
import com.example.amitdasgupta.newsappusingretrofit.adapters.RecyclerSourcesAdapter;
import com.example.amitdasgupta.newsappusingretrofit.model.SorcesResponse;
import com.example.amitdasgupta.newsappusingretrofit.model.Sources;
import com.example.amitdasgupta.newsappusingretrofit.model.Users;
import com.example.amitdasgupta.newsappusingretrofit.rest.ApiClientSource;
import com.example.amitdasgupta.newsappusingretrofit.rest.ApiSorceInterface;
import com.example.amitdasgupta.newsappusingretrofit.smaller_models.CustomSpinner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener  {
    //firebase authentication
    int flag=0;
    private FirebaseAuth mAuth;
    //sweetAlerDialog progress
    SweetAlertDialog sweetAlertDialog;
    //firebase database
    FirebaseDatabase database;
ApiSorceInterface apiSorceInterface;
    TextView navgation_email,navigation_username;
    CustomSpinner adapter1,adapter2;
    ImageView profile;
    MorphingButton signup;
    MorphingButton createaccount;
    EditText username,email,password,confirmpassword;
    String user_var,email_var,pass_var,confirm_var;
    Spinner c,cate;
    public static String[] country,category,channels;
    public static List<Sources> sources;
    String countr_y="",categor_y="",source_s;
    private static final String TAG ="Amit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/***************firebase
 *
 */          loadSources(null,null);
      //  Toast.makeText(this, category.length+country.length+"", Toast.LENGTH_SHORT).show();
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getData();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /***************manupating haeder items****************/
        View header=navigationView.getHeaderView(0);
        profile=(ImageView)header.findViewById(R.id.profile) ;
        navgation_email=(TextView)findViewById(R.id.textView);
        navigation_username=(TextView)findViewById(R.id.textView2);

        // baseActivity=new BaseActivity();
        /*morphing buton code ***************************/
        createaccount=(MorphingButton)header.findViewById(R.id.createaccount);
        morphToSquare(createaccount,500);
        createaccount.setOnClickListener(this);

        /*Sources responses taken here



        /***********************************/


    }

    private void getData() {

        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Select News")
                .customView(R.layout.custom_dialog_news, true)
                .positiveText("Search")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(countr_y.equals("") || categor_y.equals(""))
                        {
                            Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"News selected",Toast.LENGTH_LONG).show();

                                loadSources(categor_y,countr_y);
                                selectSources();

                        }
                    }
                })
                .negativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        adapter1=new CustomSpinner(MainActivity.this,country);
        adapter2=new CustomSpinner(MainActivity.this,category);
        cate = (Spinner) dialog.getCustomView().findViewById(R.id.category);
        c  = (Spinner) dialog.getCustomView().findViewById(R.id.country);
        cate.setAdapter(adapter1);
        c.setAdapter(adapter2);

        dialog.show();

        c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                countr_y = country[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                categor_y= category[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
       if (!categor_y.equals("")&&countr_y.equals(""))
        {
            loadSources(categor_y,countr_y);
        }




    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.signin) {
           signIn();

        } else if (id == R.id.myfeeds) {

        } else if (id == R.id.mytopics) {

        } else if (id == R.id.unreadnews) {

        } else if (id == R.id.topstories) {

        } else if (id == R.id.popularstories) {

        }
        else if (id == R.id.latestsories) {

        }
        else if (id == R.id.popularstories) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void loadData(ArrayList<Sources> sources)
    {
        int i=0;
        country=new String[sources.size()];
        channels=new String[sources.size()];
        category=new String[sources.size()];
        for(Sources obj:sources)
        {
            country[i]=obj.getCountry();
            channels[i]=obj.getId();
            category[i]=obj.getCategory();
            i++;
    }
        country= new HashSet<String>(Arrays.asList(country)).toArray(new String[0]);
        channels= new HashSet<String>(Arrays.asList(channels)).toArray(new String[0]);
        category= new HashSet<String>(Arrays.asList(category)).toArray(new String[0]);
        Toast.makeText(this, category.length+","+country.length+"", Toast.LENGTH_LONG).show();
}

    @Override
    public void onClick(View view) {
if(view==createaccount)
{
   /*if sign up success*/
    signUpDialog();
   /*if sign up failure*/
  // morphToFailure(signup,500,"Failure");
}
    }
    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_corner_radius_2))
                .width(dimen(R.dimen.mb_width_200))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_blue))
                .text("Sign Up")
                .colorPressed(color(R.color.mb_blue_dark));
        btnMorph.morph(square);
    }
    private void morphToFailure(final MorphingButton btnMorph, int duration,String value) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_width_150))
                .height(dimen(R.dimen.mb_height_60))
                .color(color(R.color.mb_red))
                .colorPressed(color(R.color.mb_red_dark))
                .icon(R.drawable.ic_lock)
                .text(value);
        btnMorph.morph(circle);
    }

    private void morphToSuccess(final MorphingButton btnMorph,String value) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(integer(R.integer.mb_animation))
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_width_150))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.darkblue))
                .colorPressed(color(R.color.mb_green_dark))
                .text(value);
        btnMorph.morph(circle);

    }
    private void signUpDialog()
    {    sweetAlertDialog=new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        sweetAlertDialog.setTitleText("Creating Your Account");
        sweetAlertDialog.setCancelable(false);
        MaterialDialog dialog = new MaterialDialog.Builder(MainActivity.this)
                .title("Create Profile")
                .customView(R.layout.signup_activity, true)
                .negativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        signup=(MorphingButton)dialog.getCustomView().findViewById(R.id.signup);
        username = (EditText) dialog.getCustomView().findViewById(R.id.username);
        email = (EditText) dialog.getCustomView().findViewById(R.id.useremail);
        password = (EditText) dialog.getCustomView().findViewById(R.id.userpassword);
        confirmpassword = (EditText) dialog.getCustomView().findViewById(R.id.userrepassword);
      //  morphToSquare(signup,500);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_var=username.getText().toString();
                email_var=email.getText().toString();
                pass_var=password.getText().toString();
                confirm_var=confirmpassword.getText().toString();
                 sweetAlertDialog.show();
                if(user_var.equals("") || email_var.equals("")|| pass_var.equals("")||confirm_var.equals(""))
                {
                    sweetAlertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    morphToFailure(signup,500,"Please try again");
                }
                else
                    if((!pass_var.equals(confirm_var))||(pass_var.length()<6))
                    {
                        Toast.makeText(getApplicationContext(), "Both Password must be same and atleast 6 characters long", Toast.LENGTH_SHORT).show();
                        sweetAlertDialog.dismiss();
                    }   else
                            registerUser();
            }
        });
        dialog.show();


    }

    private void signIn() {
        final EditText email;
        final EditText password;
        final MorphingButton signin;
        sweetAlertDialog=new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        sweetAlertDialog.setTitleText("Creating Your Account");
        sweetAlertDialog.setCancelable(false);
        MaterialDialog dialog = new MaterialDialog.Builder(MainActivity.this)
                .title("Create Profile")
                .customView(R.layout.signin_activity, true)
                .negativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        signin=(MorphingButton)dialog.getCustomView().findViewById(R.id.signin);
        email = (EditText) dialog.getCustomView().findViewById(R.id.signinemail);
        password = (EditText) dialog.getCustomView().findViewById(R.id.signinpassword);
        //  morphToSquare(signup,500);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_var=email.getText().toString();
                pass_var= password.getText().toString();
                sweetAlertDialog.show();
                if(email_var.equals("")|| pass_var.equals(""))
                {
                    sweetAlertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    morphToFailure(signin,500,"Please try again");
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(email_var,pass_var).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {
                           sweetAlertDialog.dismiss();
                           Toast.makeText(MainActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
                           morphToSuccess(createaccount,"Logged In");
                           morphToSuccess(signin,"Logged In");

                       }
                       else {
                           sweetAlertDialog.dismiss();
                           Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                           morphToFailure(signin,500,"Please try again");

                       }
                        }
                    });
                }
            }
        });
        dialog.show();



    }
/**************************************************************************************/
public int dimen(@DimenRes int resId) {
    return (int) getResources().getDimension(resId);
}

    public int color(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId) {
        return getResources().getInteger(resId);
    }
    /*************************************************************************/
    public void registerUser()
    {
        final DatabaseReference myRef=database.getReference("Users");
        Toast.makeText(MainActivity.this,email_var+pass_var,Toast.LENGTH_LONG).show();
        mAuth.createUserWithEmailAndPassword(email_var,pass_var).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful())
               {
                   Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                   myRef.child(mAuth.getCurrentUser().getUid()).setValue(new Users(user_var,email_var,mAuth.getCurrentUser().getUid().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful())
                      {
                          Toast.makeText(getApplicationContext(),"Account is Created",Toast.LENGTH_LONG).show();
                          morphToSuccess(signup,"Account Created");
                          morphToSuccess(createaccount,"Logged In");
                      }
                      else {
                          Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
                      }

                       }
                   });
                   sweetAlertDialog.dismiss();
               }
               else {

                   sweetAlertDialog.dismiss();
                   Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
               }
           }
       });
    }
    void loadSources(String categor_y,String countr_y)
    {
        apiSorceInterface= ApiClientSource.getClient().create(ApiSorceInterface.class);
        Call<SorcesResponse> call=apiSorceInterface.getSourceResponse(categor_y,"en",countr_y);
        call.enqueue(new Callback<SorcesResponse>() {
            @Override
            public void onResponse(Call<SorcesResponse> call, Response<SorcesResponse> response) {
                sources=response.body().getResults();
                if(flag==0)
                {
                loadData((ArrayList<Sources>) sources);
                    flag=1;}
                Toast.makeText(MainActivity.this,"Loading latest news",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SorcesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Not Connected to Internet",Toast.LENGTH_LONG).show();
            }
        });
    }
    /**************************************select sources here******************************/
    private void selectSources() {
        RecyclerView recyclerview;
        RecyclerView.LayoutManager layoutmanager;
        RecyclerSourcesAdapter adapter;
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Select Sources")
                .customView(R.layout.news_sources__menu, true)
                .positiveText("Search")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .negativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        recyclerview = (RecyclerView) dialog.getCustomView().findViewById(R.id.md_contentRecyclerView);
        layoutmanager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutmanager);
        adapter=new RecyclerSourcesAdapter(sources,MainActivity.this);
        recyclerview.setAdapter(adapter);



    }

}
