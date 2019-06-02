package com.example.myapplication;

import com.google.firebase.database.snapshot.StringNode;

import java.util.Date;

public class MatchDetail {

    public Date t1_date,t2_date;
    public String t1_toss,t2_toss;
    public String t1_score,t2_score;
    public Number t1_wicketTaken,t2_wicketTaken;
    public Number t1_total6s,t2_total6s;
    public Number t1_total4s,t2_total4s;
    public Number t1_extras,t2_extras;
    public Number t1_op,t2_op;
    public Number t1_highestScorer,t2_highestScorer;
    public String t1_highestWickets,t2_highestWickets;
    public Number t1_totalCatches,t2_totalCatches;
    public String t1_result,t2_result;

    public MatchDetail() {

    }

    public MatchDetail(Date t1_date, Date t2_date, String t1_toss, String t2_toss, String t1_score,
                       String t2_score, Number t1_wicketTaken, Number t2_wicketTaken, Number t1_total6s,
                       Number t2_total6s, Number t1_total4s, Number t2_total4s, Number t1_extras,
                       Number t2_extras, Number t1_op, Number t2_op, Number t1_highestScorer, Number t2_highestScorer,
                       String t1_highestWickets, String t2_highestWickets, Number t1_totalCatches,
                       Number t2_totalCatches, String t1_result, String t2_result)
    {
        this.t1_date = t1_date;
        this.t2_date = t2_date;
        this.t1_toss = t1_toss;
        this.t2_toss = t2_toss;
        this.t1_score = t1_score;
        this.t2_score = t2_score;
        this.t1_wicketTaken = t1_wicketTaken;
        this.t2_wicketTaken = t2_wicketTaken;
        this.t1_total6s = t1_total6s;
        this.t2_total6s = t2_total6s;
        this.t1_total4s = t1_total4s;
        this.t2_total4s = t2_total4s;
        this.t1_extras = t1_extras;
        this.t2_extras = t2_extras;
        this.t1_op = t1_op;
        this.t2_op = t2_op;
        this.t1_highestScorer = t1_highestScorer;
        this.t2_highestScorer = t2_highestScorer;
        this.t1_highestWickets = t1_highestWickets;
        this.t2_highestWickets = t2_highestWickets;
        this.t1_totalCatches = t1_totalCatches;
        this.t2_totalCatches = t2_totalCatches;
        this.t1_result = t1_result;
        this.t2_result = t2_result;
    }
}
