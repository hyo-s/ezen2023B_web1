package example.day09;

public class SumThread extends Thread{
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    // * 1~100 누적합을 구하는 함수
    @Override
    public void run() {
        for(int i=1; i<=100; i++){
            sum+=i;
        }
    }
}
