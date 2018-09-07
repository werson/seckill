package werson.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 线程池
 * @author gejian
 * @version 1.0.0
 * @since 2018/8/2
 */
public class TimingThreadPool extends ThreadPoolExecutor {

	private final Logger log = Logger.getLogger("TimingThreadPool");

	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected void beforeExecute(Thread t, Runnable r){
		super.beforeExecute(t, r);
		log.fine(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}

	protected void afterExecute(Runnable r, Throwable t){
		try{
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.fine(String.format("Thread %s: end %s take time %dns", t, r, taskTime));
		} catch (Exception e){
			super.afterExecute(r, t);
		}
	}

	protected void terminated(){
		try{
			log.info(String.format("thread : avg time %dns", totalTime.get()/numTasks.get()));
		}catch (Exception e){
			super.terminated();
		}
	}

}
