//Pardon me for the ugly code!
//Do whatever you want to!
//Aravind.V.S
//www.aravindvs.com

package com.aravind.x_control;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import com.io.socket.IOAcknowledge;
import com.io.socket.IOCallback;
import com.io.socket.SocketIO;
import com.io.socket.SocketIOException;

public class MainActivity extends Activity {

	
	SocketIO socket;
	EditText t2;
	Button b;
	Button b2;
    Switch s1,s2,s3;
    @TargetApi(14)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
         b=(Button)findViewById(R.id.button1);
         t2=(EditText)findViewById(R.id.editText1);
	     b2=(Button)findViewById(R.id.button2);
	     s1=(Switch)findViewById(R.id.switch1);
	     s2=(Switch)findViewById(R.id.switch2);
	     s3=(Switch)findViewById(R.id.switch3);
	     b2.setEnabled(false);
	     s1.setEnabled(false);
	     s2.setEnabled(false);
	     s3.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @TargetApi(14)
	public void login(View v)
    {
    	try {
			socket = new SocketIO("http://192.168.2.186:80");
			b.setEnabled(false);
			t2.setEnabled(false);
			b2.setEnabled(true);
			s1.setEnabled(true);
		    s2.setEnabled(true);
		    s3.setEnabled(true);
			socket.connect(new IOCallback() {
	            public void onMessage(JSONObject json, IOAcknowledge ack) {
	                try {
	                    System.out.println("Server said:" + json.toString(2));
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            }

				public void onDisconnect() {
	
				}

				public void onConnect() {
					System.out.println("Connected!");
					try {
						socket.emit("validate", new JSONObject().put("msgdata",t2.getText()));
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				public void onMessage(String data, IOAcknowledge ack) {
					System.out.println("message:"+data);
				}
             public void on(String event, IOAcknowledge ack, Object... args) {
            	    
					System.out.println("event:"+args);
					
				}

				public void onError(SocketIOException socketIOException) {
					// TODO Auto-generated method stub
					
				}
		      });
    	}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @SuppressLint("NewApi")
	public void fan(View V)
    {
    	try {
    		final Switch s=(Switch)findViewById(R.id.switch1);
    		boolean state = s.isChecked();
			socket.emit("FanToggle", new JSONObject().put("msgdata", state));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @SuppressLint("NewApi")
	public void light(View V)
    {
    	try {
    		final Switch s=(Switch)findViewById(R.id.switch2);
    		boolean state = s.isChecked();
			socket.emit("LightToggle", new JSONObject().put("msgdata", state));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @SuppressLint("NewApi")
	public void plug(View V)
    {
    	try {
    		final Switch s=(Switch)findViewById(R.id.switch3);
    		boolean state = s.isChecked();
			socket.emit("PlugToggle", new JSONObject().put("msgdata", state));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @TargetApi(14)
	public void disco(View v)
    {
    	try {
			socket.emit("disco", new JSONObject().put("msgdata","G"));
			b.setEnabled(true);
			t2.setEnabled(true);
			b2.setEnabled(false);
			s1.setEnabled(false);
		     s2.setEnabled(false);
		     s3.setEnabled(false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
