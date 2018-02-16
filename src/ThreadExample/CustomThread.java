package ThreadExample;
/**
 * ffs, couldn't keep up at this point. The example was about threads running in parallel, account, deposit whitdraw etc.
 * @author Slobodan
 *
 */
public class CustomThread  implements Runnable{

	private Thread theThread;
	private boolean stopThread = false;
	
	public void start()
	{
		if(theThread == null) {
			theThread = new Thread(this);
			theThread.start();
		}
	}
	public void setStopThread(boolean aValue) {
		stopThread = aValue;
	}
	
	@Override
	public void run() {
		 
		while(true){
			if(stopThread) {
				break;
			}
		}
	}

}

/*
 * Front end to server i onda server 2 thread-a jedan da save-je sliku u bazu a drugi da moze da radi program
 * tako da korisnik mze da nastavi browse-ovanje na web sajtu
 */