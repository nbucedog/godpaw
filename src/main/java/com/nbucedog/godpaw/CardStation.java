package com.nbucedog.godpaw;

/**
 * Created by cthLlxl on 2017/5/10.
 */

public class CardStation {
    public int Role;
    private String ipv4;
    private boolean setter;
    public CardStation(){
        this.Role = 0;
        this.ipv4 = "/255.255.255.255";
        this.setter = true;
    }
    public boolean readble(String ip){
        if(ip.equals(null)){
            return false;
        }
        if (this.setter) {
            this.ipv4 = ip;
            this.setter = false;
            return true;
        }
        else if(ip.equals(this.ipv4)){
            return true;
        }
        else
            return false;
    }
}
