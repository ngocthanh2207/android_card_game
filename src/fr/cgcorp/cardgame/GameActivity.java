package fr.cgcorp.cardgame;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	ArrayAdapter<String> chatAdapter;
	ArrayList<Card> deck = new ArrayList<Card>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        fillRandomDeck();
        
        bindListeners();
        
        initChat();
        
    }
    
    protected void bindListeners() {
        findViewById(R.id.my_monster0).setOnDragListener(new MyDragListener());
        findViewById(R.id.my_monster1).setOnDragListener(new MyDragListener());
        findViewById(R.id.my_monster2).setOnDragListener(new MyDragListener());
        findViewById(R.id.my_monster3).setOnDragListener(new MyDragListener());
        findViewById(R.id.hand0).setOnDragListener(new MyDragListener());
        findViewById(R.id.hand1).setOnDragListener(new MyDragListener());
        findViewById(R.id.hand2).setOnDragListener(new MyDragListener());
        findViewById(R.id.hand3).setOnDragListener(new MyDragListener());
    }
    
    protected void initChat() {
        chatAdapter = new ArrayAdapter<String>(this, R.layout.chat_line);
		ListView lv = (ListView)findViewById(R.id.chat);
		lv.setStackFromBottom(true);
		lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		lv.setAdapter(chatAdapter);
		
		fillFakeChat();
    }
    
    protected void fillRandomDeck() {
        deck.add(new Card(CardSet.getCardDefinition("baba")));
        deck.add(new Card(CardSet.getCardDefinition("baba")));
        deck.add(new Card(CardSet.getCardDefinition("baba")));
        deck.add(new Card(CardSet.getCardDefinition("bobo")));
        deck.add(new Card(CardSet.getCardDefinition("bobo")));
        deck.add(new Card(CardSet.getCardDefinition("bobo")));
        deck.add(new Card(CardSet.getCardDefinition("bibi")));
        deck.add(new Card(CardSet.getCardDefinition("bibi")));
        deck.add(new Card(CardSet.getCardDefinition("bibi")));
        deck.add(new Card(CardSet.getCardDefinition("bubu")));
        deck.add(new Card(CardSet.getCardDefinition("bubu")));
        deck.add(new Card(CardSet.getCardDefinition("bubu")));
        
        Collections.shuffle(deck);
    }
    
    protected void fillFakeChat() {
        for (int i=0; i<20; ++i) {
        	addLineToChat("coucou "+i);
        }
    }

    private final class MyTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable validDropShape = getResources().getDrawable(R.drawable.shape_validdroptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
        	FrameLayout container = null;
            View view = (View) event.getLocalState();
        	ViewGroup owner = null;
        	
            switch (event.getAction()) {
            
            case DragEvent.ACTION_DRAG_STARTED:
            	try {
	            	container = (FrameLayout) v;
	            	if (container.getChildCount() < 1) {
	                    v.setBackgroundDrawable(validDropShape);
	            		return true;
	            	}
            	}
            	catch (Exception e) {
            		Log.e("MainActivity", "ACTION DRAG STARTED failed", e);
            	}
        		return false;
            
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundDrawable(enterShape);
                break;

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
            	return true;
            
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundDrawable(validDropShape);
                break;
            
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                container = (FrameLayout) v;
                container.addView(view);
                Toast.makeText(GameActivity.this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                break;
                
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundDrawable(normalShape);
                
                if (!event.getResult()) {
                	Toast.makeText(GameActivity.this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                };
                break;
                
            default:
            	Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                break;
            }
            return true;
        }
    }
    
    public FrameLayout getFreeHandZone() {
    	ViewGroup view = (ViewGroup) findViewById(R.id.hand_zone);
    	for (int i=0; i<view.getChildCount(); ++i) {
    		FrameLayout fl = (FrameLayout) view.getChildAt(i);
    		if (fl.getChildCount() < 1) {
    			return fl;
    		}
    	}
    	return null;
    }
    
    public void onCLickDraw(View view) {
    	Log.i("MainActivity", "onClickDraw");
    	FrameLayout fl = getFreeHandZone();
    	if (fl != null) {
    		if (deck.size() > 0) {
    	    	Toast.makeText(this, "Player drawed a card", Toast.LENGTH_SHORT).show();
    	    	addLineToChat("Player drawed a card");
	    		Card card = deck.remove(0);
	    		Log.i("MainActivity", "Player drawed "+card.getDefinition().getName());
	    		View cardView = getLayoutInflater().inflate(R.layout.card, null);
	    		cardView.setOnTouchListener(new MyTouchListener());
	    		TextView tv = (TextView)cardView.findViewById(R.id.name);
	    		tv.setText(card.getDefinition().getName());
	    		tv = (TextView)cardView.findViewById(R.id.points);
	    		tv.setText(""+card.getDefinition().getPoints());
	    		fl.addView(cardView);
    		}
    		else {
    			addLineToChat("Deck is empty");
    	    	Toast.makeText(this, "Deck is empty", Toast.LENGTH_SHORT).show();
    		}
    	}
    	else {
        	Log.i("MainActivity", "Hand is full");
	    	Toast.makeText(this, "Hand is full", Toast.LENGTH_SHORT).show();
    	}
    }
    
    public void addLineToChat(String msg) {
    	Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss] ");
    	chatAdapter.add(sdf.format(cal.getTime()) + msg);
    }
}
