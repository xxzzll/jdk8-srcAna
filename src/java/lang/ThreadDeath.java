/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang;

/**
 * An instance of {@code ThreadDeath} is thrown in the victim thread
 * when the (deprecated) {@link Thread#stop()} method is invoked.
 *
 * 当一个线程死亡的时候会排除一个ThreadDeath对象实例，
 * 当线程调用已经废弃的stop()方法时，会调用该对象
 *
 * <p>An application should catch instances of this class only if it
 * must clean up after being terminated asynchronously.  If
 * {@code ThreadDeath} is caught by a method, it is important that it
 * be rethrown so that the thread actually dies.
 *
 * 只有当一个应用异步关闭需要清理时，会捕获该类的一个实例。
 * 当ThreadDeath被一个方法捕获的时候，该对象会被重新抛出。
 *
 * <p>The {@linkplain ThreadGroup#uncaughtException top-level error
 * handler} does not print out a message if {@code ThreadDeath} is
 * never caught.
 *
 * <p>The class {@code ThreadDeath} is specifically a subclass of
 * {@code Error} rather than {@code Exception}, even though it is a
 * "normal occurrence", because many applications catch all
 * occurrences of {@code Exception} and then discard the exception.
 *
 * @since   JDK1.0
 */

public class ThreadDeath extends Error {
    private static final long serialVersionUID = -4417128565033088268L;
}
