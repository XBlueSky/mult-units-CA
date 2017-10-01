public class Bidder {
    private int bid,type;
    private float bidPerNum;
    private float critical;
    private String[] bundle;
    private String name;
    private int checked  = -1;
    public boolean set = false;

    public Bidder (String name){
        setname(name);
    }

    public void setname(String name){
        this.name = name;
    }

    public void setbid(int bid){
        this.bid = bid;
    }

    public void setbundle(String bundle){
        this.bundle = bundle.split(",");
        int sum = 0;

        for(int i = 0; i < this.bundle.length; i++) {
            sum += Integer.parseInt(this.bundle[i]);
        }
        this.type = sum;
        this.bidPerNum = (float)bid / sum;
    }

    public void setchecked(int checked){
        this.checked = checked;
    }
    public void setcritical(float critical){
        this.critical = critical;
    }

    public String getname(){
        return this.name;
    }

    public int getbid(){
        return this.bid;
    }

    public int gettype(){
        return this.type;
    }

    public String[] getbundle(){
        return this.bundle;
    }

    public int getchecked() {
        return this.checked;
    }

    public float getcritical(){
        return this.critical;
    }

    public float getbidPerNum(){
        return this.bidPerNum;
    }

    public static boolean disjointSet(String[] A,String[] total){
        for(int i=0;i<A.length;i++){
            if(Integer.parseInt(A[i]) > Integer.parseInt(total[i]))
                return false;
        }
        return true;
    }

    public static Bidder max(Bidder[] players){
        Bidder max = new Bidder("max");

        for(Bidder player : players)
            if(player.getbidPerNum() > max.getbidPerNum())
                max = player;

        return max;
    }

    public static void sort(Bidder[] players){
        Bidder temp = new Bidder("temp");
        for(int i=0;i<(players.length-1);i++)
            for(int j=0;j<(players.length-i-1);j++)
                if(players[j].getbidPerNum()<players[j+1].getbidPerNum()){
                    temp = players[j];
                    players[j] = players[j+1];
                    players[j+1] = temp;
                }
    }


}