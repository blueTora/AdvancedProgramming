package GameHandler;

import java.util.concurrent.*;

/**
 * This class holds a global thread-pool for executing our threads.
 * 
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class ThreadPool {
	
	private static ExecutorService executor;
	
	/**
	 * Initializes a new CachedThreadPool.
	 */
	public static void init() {
		executor = Executors.newCachedThreadPool();
	}

	public static void execute(Runnable r) {
		if (executor == null)
			init();
		executor.execute(r);
	}

	public static void shutdown() {
		executor.shutdown();
	}

	public static void shutdownNow() {
		executor.shutdownNow();
	}
}
