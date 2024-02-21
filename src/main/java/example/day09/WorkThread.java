package example.day09;

public class WorkThread extends Thread {
    public boolean work = true;

    public WorkThread(String name){
        setName(name); // 스레드 이름 변경
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("e = " + e);
            }
            if(work){
                System.out.println("getName() = " + getName());
            }else {
                System.out.println("1"); // 점유 --> 처리 --> 대기
                Thread.yield(); // 점유 --> 양보 --> 대기 : 점유 상태 되었을 때 대기상태로 돌아감
                System.out.println("2"); // 점유 --> 처리 --> 대기
            }
        }
    }
}
