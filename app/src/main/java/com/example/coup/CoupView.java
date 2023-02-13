package com.example.coup;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class CoupView extends View {
    private final int width = getVieWidth(),height = getVieHeight(), back_card_height = 200, back_card_width = back_card_height/2,padding = 20;
    private Canvas ThisCanvas;
    private CardType CardDetailsType = null;
    private Deck deck = new Deck();
    private Card[] myCard = deck.TakeTwoCards();
    private PersonalBank personalBank = new PersonalBank();
    private boolean myTurn = true,drawBankDetails = false;//TODO: change -> = true
    private String game_name,myPlayerName;

    public CoupView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ThisCanvas = canvas;
        updateMoves();
        drawBoard();
        drawBackCards();
        drawCards();
        drawChips();
        drawWords();
        drawCardDetails(CardDetailsType);
        drawBankDetails();
    }

    private void drawCardDetails(CardType cardType) {
        if(cardType != null) {
            int card_height = (height - width) / 2 - padding * 4, card_width = card_height / 2, redId = 0;
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setTextSize(padding*2);
            ThisCanvas.drawText("Actions :", padding * 5 + card_width, padding * 3, paint);
            switch (cardType) {
                case Captain: {
                    redId = R.drawable.captain;
                    ThisCanvas.drawText("Steal two coins from a chosen opponent", padding * 5 + card_width, padding * 5, paint);
                    ThisCanvas.drawText("Block steal", padding * 5 + card_width, padding * 7, paint);
                    drawCardsActionButton(2,"Steal","Block");
                    break;
                }
                case Ambassador: {
                    redId = R.drawable.ambassador;
                    ThisCanvas.drawText("Taking two cards from deck - ", padding * 5 + card_width, padding * 5, paint);
                    ThisCanvas.drawText("and choosing two to return to the deck", padding * 5 + card_width, padding * 7, paint);
                    ThisCanvas.drawText("Block steal", padding * 5 + card_width, padding * 9, paint);
                    drawCardsActionButton(2,"Change","Block");
                    break;
                }
                case Contessa: {
                    redId = R.drawable.contessa;
                    ThisCanvas.drawText("Block assassination", padding * 5 + card_width, padding * 5, paint);
                    drawCardsActionButton(1,"Block",null);
                    break;
                }
                case Duke: {
                    redId = R.drawable.duke;
                    ThisCanvas.drawText("Take 3 coins fro the bank", padding * 5 + card_width, padding * 5, paint);
                    ThisCanvas.drawText("Stop Opponent from taking -", padding * 5 + card_width, padding * 7, paint);
                    ThisCanvas.drawText("two coins from bank", padding * 5 + card_width, padding * 9, paint);
                    drawCardsActionButton(2,"3 coins","Stop");
                    break;
                }
                case Assassin: {
                    redId = R.drawable.assasain;
                    ThisCanvas.drawText("Assassin Opponent with 3 coins", padding * 5 + card_width, padding * 5, paint);

                    drawCardsActionButton(1,"Assassin",null);
                    break;
                }
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), redId);
            ThisCanvas.drawBitmap(bitmap, null, new Rect(padding * 2, padding * 2, padding * 2 + card_width, padding * 2 + card_height), null);
        }

        FireStoreHelper fireStoreHelper = new FireStoreHelper(game_name,getContext());
        fireStoreHelper.addMove("blue","kkk");
    }

    private void drawCardsActionButton(int numButton,String ActionName1,String ActionName2) {
        if(myTurn) {
            int card_height = (height - width) / 2 - padding * 4, card_width = card_height / 2;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orange_buttonn);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            paint.setTextSize(padding * 2);

            ThisCanvas.drawBitmap(bitmap, null, new Rect(padding * 5 + card_width, (height - width) / 2 - card_width / 2, padding * 5 + card_width * 2, (height - width) / 2 - padding), null);
            ThisCanvas.drawText(ActionName1, padding * 7 + card_width, (height - width) / 2 - card_width / 2 + padding * 3, paint);

            if (numButton == 2) {
                ThisCanvas.drawBitmap(bitmap, null, new Rect(padding * 8 + card_width * 2, (height - width) / 2 - card_width / 2, padding * 8 + card_width * 3, (height - width) / 2 - padding), null);
                ThisCanvas.drawText(ActionName2, padding * 10 + card_width * 2, (height - width) / 2 - card_width / 2 + padding * 3, paint);
            }
        }
    }

    private void drawCards() {
        int card_width = (width-6*2*padding)/5 , card_height = height - (height-width)/2 -width -padding*6 ;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.captain);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding*2 ,height-padding*3-card_height ,padding*2 + card_width,height-padding*3),null);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ambassador);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding*4 + card_width,height-padding*3-card_height ,padding*4 + card_width*2,height-padding*3),null);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.contessa);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding*6 + card_width*2,height-padding*3-card_height ,padding*6 + card_width*3,height-padding*3),null);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.duke);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding*8 + card_width*3,height-padding*3-card_height ,padding*8 + card_width*4,height-padding*3),null);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.assasain);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding*10 + card_width*4,height-padding*3-card_height ,padding*10 + card_width*5,height-padding*3),null);
    }

    private void drawChips() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pocker_chip);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding*2 + back_card_height ,((height)/2)-padding - back_card_width/2 ,back_card_height+padding*2+back_card_width,((height)/2)-padding+back_card_width/2),null);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width-back_card_height-padding*2-back_card_width ,((height)/2)-padding - back_card_width/2 ,width-back_card_height-padding*2,((height)/2)-padding+back_card_width/2),null);


        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2- padding - back_card_width + padding*2,(height-width)/2+back_card_height+padding*2,width/2-padding + padding*2,(height-width)/2+back_card_height+back_card_width+padding*2),null);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2- padding - back_card_width + padding*2,height-(height-width)/2-back_card_height-padding*2 - back_card_width,width/2-padding  + padding*2,height-(height-width)/2-back_card_height-padding*2),null);

    }

    private void drawWords() {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(35);
        ThisCanvas.drawText("X infinity", width/2-back_card_width/2, height/2+back_card_width+padding*2, paint);
        paint.setTextSize(padding*2);
        ThisCanvas.drawText("X 2", width/2 + padding*2,height-(height-width)/2-back_card_height-padding - back_card_width/2 , paint);

        ThisCanvas.drawText("X 2", width/2 + padding*2, (height-width)/2+back_card_height+back_card_width/2+padding*3 , paint);

        ThisCanvas.drawText("X 2", back_card_height+padding*3, ((height)/2) +back_card_width, paint);

        ThisCanvas.drawText("X 2",width-back_card_height-padding-back_card_width , ((height)/2) +back_card_width, paint);
    }

    private void drawBoard(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.board);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(0 ,((height-width)/2),width,(height-width)/2+width),null);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bank);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2-back_card_width ,((height)/2-back_card_width),width/2+back_card_width,(height)/2+back_card_width),null);
    }
    private void drawBackCards(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back_card);

        //The two cards above
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2-padding - back_card_width,((height-width)/2)+padding,width/2-padding ,(height-width)/2+back_card_height+padding),null);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2+padding ,((height-width)/2)+padding,width/2+back_card_width+padding,(height-width)/2+back_card_height+padding),null);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back_card_horizantel);

        //the two cards on the right
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding ,((height)/2)-padding - back_card_width,back_card_height+padding,(height)/2-padding),null);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(padding ,((height)/2)+padding,back_card_height+padding,(height)/2+back_card_width+padding),null);

        //the two cards on the left
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width-back_card_height-padding ,((height)/2)-padding - back_card_width,width-padding,(height)/2-padding),null);
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width-back_card_height-padding ,((height)/2)+padding,width-padding,(height)/2+back_card_width+padding),null);

        drawMyCards();
    }
    private void drawMyCards(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), myCard[0].getResId());
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2-padding - back_card_width,height-(height-width)/2-back_card_height-padding,width/2-padding ,height-(height-width)/2-padding),null);

        bitmap = BitmapFactory.decodeResource(getResources(),myCard[1].getResId());
        ThisCanvas.drawBitmap(bitmap,null,new Rect(width/2+padding ,height-(height-width)/2-back_card_height-padding,width/2+back_card_width+padding,height-(height-width)/2-padding),null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if(cardByPosition((int)event.getX(),(int)event.getY())){
                invalidate();
                return true;
            }
            if(bankIsPressed((int)event.getX(),(int)event.getY())){
                drawBankDetails = true;
                invalidate();
                return true;
            }
            if(isButtonPressed((int)event.getX(),(int)event.getY())){

            }
        }
        return true;
    }

    private boolean isButtonPressed(int x, int y) {
        int card_height = (height - width) / 2 - padding * 4, card_width = card_height / 2;
        int left = padding * 4 + card_height,right = padding * 3 + card_height + card_width,top = (height - width) / 2 - card_width / 2,bottom = (height - width) / 2 - padding;


        //bank's buttons
        //1 coin button
        if(x>= left && x<= right && y>= top && y<= bottom){

            return true;
        }
        left = padding * 6 + card_height + card_width;
        right = padding * 6 + card_height + card_width * 2;
        top = (height - width) / 2 - card_width / 2;
        bottom = (height - width) / 2 - padding;
        //2 coin button
        if(x>= left && x<= right && y>= top && y<= bottom){

            return true;
        }

        //card's buttons

        left = padding * 5 + card_width;
        right = padding * 5 + card_width * 2;
        top = (height - width) / 2 - card_width / 2;
        bottom = (height - width) / 2 - padding;
        //left button
        if(x>= left && x<= right && y>= top && y<= bottom){
            switch (CardDetailsType) {
                case Duke: {
                    Duke duke = new Duke();
                    duke.attack();
                    break;
                }
                case Assassin: {
                    Assassin assassin = new Assassin();
                    assassin.attack();
                    break;
                }
                case Ambassador: {
                    Ambassador ambassador = new Ambassador();
                    ambassador.attack();
                    break;
                }
                case Captain:
                    Captain captain = new Captain();
                    captain.attack();
                    break;
                case Contessa: {
                    Contessa contessa = new Contessa();
                    contessa.defend();
                    break;
                }
            }
            return true;
        }

        left = padding * 8 + card_width * 2;
        right = padding * 8 + card_width * 3;
        top = (height - width) / 2 - card_width / 2;
        bottom = (height - width) / 2 - padding;
        //right button
        if(x>= left && x<= right && y>= top && y<= bottom){

            switch (CardDetailsType) {
                case Captain: {
                    Captain captain = new Captain();
                    captain.defend();
                    break;
                }
                case Ambassador: {
                    Ambassador ambassador = new Ambassador();
                    ambassador.defend();
                    break;
                }
                case Contessa:
                    break;
                case Duke: {
                    Duke duke = new Duke();
                    duke.defend();
                    break;
                }
                case Assassin: {
                    break;
                }
            }
            return true;
        }

        return false;
    }

    private void drawBankDetails() {
        if (drawBankDetails) {
            int card_height = (height - width) / 2 - padding * 4;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bank);
            Paint paint = new Paint();

            resetTop();

            ThisCanvas.drawBitmap(bitmap, null, new Rect(padding * 2, padding * 2, padding * 2 + card_height, padding * 2 + card_height), null);

            paint.setColor(Color.YELLOW);
            paint.setTextSize(padding*2);
            ThisCanvas.drawText("Actions :", padding * 5 + card_height, padding * 3, paint);
            ThisCanvas.drawText("Take 1 coin from bank", padding * 5 + card_height, padding * 5, paint);
            ThisCanvas.drawText("Take 2 coin from bank", padding * 5 + card_height, padding * 7, paint);

            drawBankDetails = false;
            CardDetailsType = null;

            drawBankButton();
        }
    }

    private void drawBankButton() {
        if(myTurn) {
            int card_height = (height - width) / 2 - padding * 4, card_width = card_height / 2;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orange_buttonn);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            paint.setTextSize(padding * 2);
            ThisCanvas.drawBitmap(bitmap, null, new Rect(padding * 4 + card_height, (height - width) / 2 - card_width / 2, padding * 3 + card_height + card_width, (height - width) / 2 - padding), null);
            ThisCanvas.drawText("1 coin", padding * 6 + card_height, (height - width) / 2 - card_width / 2 + padding * 3, paint);

            ThisCanvas.drawBitmap(bitmap, null, new Rect(padding * 6 + card_height + card_width, (height - width) / 2 - card_width / 2, padding * 6 + card_height + card_width * 2, (height - width) / 2 - padding), null);
            ThisCanvas.drawText("2 coin", padding * 8 + card_height + card_width, (height - width) / 2 - card_width / 2 + padding * 3, paint);
        }
    }

    private void resetTop() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        ThisCanvas.drawRect( new Rect(0, 0, width, (height-width)/2),paint);
    }

    private boolean bankIsPressed(int x, int y) {
        if(x>=width/2-back_card_width && x<= width/2+back_card_width && y>=((height)/2-back_card_width) && y<=(height)/2+back_card_width){
            return true;
        }
        return false;
    }

    private boolean cardByPosition(int X,int Y){
        int card_height = height - (height-width)/2 -width -padding*6;
        int card_width = (width-6*2*padding)/5 ;
        if(!(Y >= height-padding*3-card_height)){
            return false;
        }
        if (X >= padding * 2 && X <= (padding * 2 + card_width)) {
            if(CardDetailsType != CardType.Captain) {
                CardDetailsType = CardType.Captain;
            }
            else CardDetailsType = null;
        }
        else if (X >= padding * 4 + card_width && X <= padding * 4 + card_width * 2) {
            if(CardDetailsType != CardType.Ambassador) {
                CardDetailsType = CardType.Ambassador;
            }
            else CardDetailsType = null;
        }
        else if (X >= padding * 6 + card_width * 2 && X <= padding * 6 + card_width * 3) {
            if(CardDetailsType != CardType.Contessa) {
                CardDetailsType = CardType.Contessa;
            }
            else CardDetailsType = null;
        }
        else if (X >= padding * 8 + card_width * 3 && X <= padding * 8 + card_width * 4) {
            if(CardDetailsType != CardType.Duke) {
                CardDetailsType = CardType.Duke;
            }
            else CardDetailsType = null;
        }
        else if (X >= padding * 10 + card_width * 4 && X <= padding * 10 + card_width * 5) {
            if(CardDetailsType != CardType.Assassin) {
                CardDetailsType = CardType.Assassin;
            }
            else CardDetailsType = null;
        }
        return true;
    }

    public void updateMoves(){
        if(myTurn) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Coup games").document(game_name).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value.get("Victory") != null) {
                        if (value.get("Victory").equals("n")) {//TODO: change to a real thing
                            Toast.makeText(getContext(), "The winner is you", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), "please get out", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "The loser is you", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), "HA HA HA HA", Toast.LENGTH_SHORT).show();
                        }
                        db.collection("Coup games").document(game_name).delete();
                    }
                }
            });
        }
    }
    public CoupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CoupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private int getVieWidth(){
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }
    private int getVieHeight() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
    public void setGame_name(String game_name){
        this.game_name = game_name;
    }

    public void setMyPlayerName(String myPlayerName) {
        this.myPlayerName = myPlayerName;
    }
}
