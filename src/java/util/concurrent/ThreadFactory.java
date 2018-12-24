/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent;

/**
 * 创建线程的工具类
 * 避免了{@link Thread#Thread(Runnable) new Thread}该类的硬链接使用
 * 让应用可以使用更多具有特殊线程子类、优先级等。
 *
 * An object that creates new threads on demand.  Using thread factories
 * removes hardwiring of calls to {@link Thread#Thread(Runnable) new Thread},
 * enabling applications to use special thread subclasses, priorities, etc.
 *
 * <p>
 * The simplest implementation of this interface is just:
 *  <pre> {@code
 * class SimpleThreadFactory implements ThreadFactory {
 *   public Thread newThread(Runnable r) {
 *     return new Thread(r);
 *   }
 * }}</pre>
 *
 * The {@link Executors#defaultThreadFactory} method provides a more
 * useful simple implementation, that sets the created thread context
 * to known values before returning it.
 * @since 1.5
 * @author Doug Lea
 */
public interface ThreadFactory {

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     *         create a thread is rejected
     */
    Thread newThread(Runnable r);
}
