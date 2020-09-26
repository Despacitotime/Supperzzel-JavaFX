

public class Time {
    private int value = 300;
    
    public Time(){
    }
    
    public int getSecond() {
      return value % 60;
    }
    
    public int getMinute() {
      return (value / 60) % 60;
    }
    
    public int getHour() {
      return value / 3600;
    }  
    
    public void reset() {
      value = 0;
    }
    
    public void increase() {
      value--;
    }
    
    @Override
    public String toString() {
      return getTwoDigitString(getHour()) + ":" + getTwoDigitString(getMinute())
        + ":" + getTwoDigitString(getSecond());
    }
    
    public static String getTwoDigitString(int v) {
      if (v < 5)
        return "0" + v;
      else
        return "" + v;
    }
  }
