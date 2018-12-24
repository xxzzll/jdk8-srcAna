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
 * Written by Doug Lea and Josh Bloch with assistance from members of
 * JCP JSR-166 Expert Group and released to the public domain, as explained
 * at http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util;

/**
 * Deque是一种支持两端插入和删除的线性集合结构。
 * Deque是double ended queue的缩写。通常Deque是没有容量限制的，
 * 也可以设置Deque的容量。
 *
 * A linear collection that supports element insertion and removal at
 * both ends.  The name <i>deque</i> is short for "double ended queue"
 * and is usually pronounced "deck".  Most {@code Deque}
 * implementations place no fixed limits on the number of elements
 * they may contain, but this interface supports capacity-restricted
 * deques as well as those with no fixed size limit.
 *
 * Deque接口对于插入，删除和检查元素的方法都有两种形式，一种在执行
 * 失败的时候抛出异常，另一种执行失败的时候返回一个特殊值。后一种
 * 操作形式多用于具有规定容量的Deque，并且insert()操作时不会失败的。
 *
 * <p>This interface defines methods to access the elements at both
 * ends of the deque.  Methods are provided to insert, remove, and
 * examine the element.  Each of these methods exists in two forms:
 * one throws an exception if the operation fails, the other returns a
 * special value (either {@code null} or {@code false}, depending on
 * the operation).  The latter form of the insert operation is
 * designed specifically for use with capacity-restricted
 * {@code Deque} implementations; in most implementations, insert
 * operations cannot fail.
 *
 * <p>The twelve methods described above are summarized in the
 * following table:
 *
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 * <caption>Summary of Deque methods</caption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>First Element (Head)</b></td>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Last Element (Tail)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Special value</em></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Special value</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td>{@link Deque#addFirst addFirst(e)}</td>
 *    <td>{@link Deque#offerFirst offerFirst(e)}</td>
 *    <td>{@link Deque#addLast addLast(e)}</td>
 *    <td>{@link Deque#offerLast offerLast(e)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Remove</b></td>
 *    <td>{@link Deque#removeFirst removeFirst()}</td>
 *    <td>{@link Deque#pollFirst pollFirst()}</td>
 *    <td>{@link Deque#removeLast removeLast()}</td>
 *    <td>{@link Deque#pollLast pollLast()}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Examine</b></td>
 *    <td>{@link Deque#getFirst getFirst()}</td>
 *    <td>{@link Deque#peekFirst peekFirst()}</td>
 *    <td>{@link Deque#getLast getLast()}</td>
 *    <td>{@link Deque#peekLast peekLast()}</td>
 *  </tr>
 * </table>
 *
 * Deque接口继承了Queue接口，当Deque用作队列的时候，具有FIFO性质，元素从Deque的尾部加入，
 * 从头部移除，Queue接口的方法和Deque接口的方法严格对应。
 *
 * <p>This interface extends the {@link Queue} interface.  When a deque is
 * used as a queue, FIFO (First-In-First-Out) behavior results.  Elements are
 * added at the end of the deque and removed from the beginning.  The methods
 * inherited from the {@code Queue} interface are precisely equivalent to
 * {@code Deque} methods as indicated in the following table:
 *
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 * <caption>Comparison of Queue and Deque methods</caption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>{@code Queue} Method</b></td>
 *    <td ALIGN=CENTER> <b>Equivalent {@code Deque} Method</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link java.util.Queue#add add(e)}</td>
 *    <td>{@link #addLast addLast(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link java.util.Queue#offer offer(e)}</td>
 *    <td>{@link #offerLast offerLast(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link java.util.Queue#remove remove()}</td>
 *    <td>{@link #removeFirst removeFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link java.util.Queue#poll poll()}</td>
 *    <td>{@link #pollFirst pollFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link java.util.Queue#element element()}</td>
 *    <td>{@link #getFirst getFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link java.util.Queue#peek peek()}</td>
 *    <td>{@link #peek peekFirst()}</td>
 *  </tr>
 * </table>
 *
 * Deque也能用作具有LIFO性质的栈的数据结构，当用作栈的时候，
 * 元素push和pop都是从Deque头部进行。
 *
 * <p>Deques can also be used as LIFO (Last-In-First-Out) stacks.  This
 * interface should be used in preference to the legacy {@link Stack} class.
 * When a deque is used as a stack, elements are pushed and popped from the
 * beginning of the deque.  Stack methods are precisely equivalent to
 * {@code Deque} methods as indicated in the table below:
 *
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 * <caption>Comparison of Stack and Deque methods</caption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>Stack Method</b></td>
 *    <td ALIGN=CENTER> <b>Equivalent {@code Deque} Method</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #push push(e)}</td>
 *    <td>{@link #addFirst addFirst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #pop pop()}</td>
 *    <td>{@link #removeFirst removeFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #peek peek()}</td>
 *    <td>{@link #peekFirst peekFirst()}</td>
 *  </tr>
 * </table>
 *
 * 注意：peek()方法在栈里的实现，是从Deque的头部取出元素。
 *
 * <p>Note that the {@link #peek peek} method works equally well when
 * a deque is used as a queue or a stack; in either case, elements are
 * drawn from the beginning of the deque.
 *
 * <p>This interface provides two methods to remove interior
 * elements, {@link #removeFirstOccurrence removeFirstOccurrence} and
 * {@link #removeLastOccurrence removeLastOccurrence}.
 *
 * 和List不同的是，Deque没有提供基于index的数据访问方式。
 *
 * <p>Unlike the {@link List} interface, this interface does not
 * provide support for indexed access to elements.
 *
 * 虽然Deque的实现类都没有严格要求不能插入null元素，但是强烈推荐这么做。
 * 因为null是很多方法的一个特殊的返回值。
 *
 * <p>While {@code Deque} implementations are not strictly required
 * to prohibit the insertion of null elements, they are strongly
 * encouraged to do so.  Users of any {@code Deque} implementations
 * that do allow null elements are strongly encouraged <i>not</i> to
 * take advantage of the ability to insert nulls.  This is so because
 * {@code null} is used as a special return value by various methods
 * to indicated that the deque is empty.
 *
 * <p>{@code Deque} implementations generally do not define
 * element-based versions of the {@code equals} and {@code hashCode}
 * methods, but instead inherit the identity-based versions from class
 * {@code Object}.
 *
 * <p>This interface is a member of the <a
 * href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author Doug Lea
 * @author Josh Bloch
 * @since  1.6
 * @param <E> the type of elements held in this collection
 */
public interface Deque<E> extends Queue<E> {
    /**
     *  在Deque的头部插入元素
     *  如果Deque的容量不够，抛出IllegalStateException。
     *  如果是插入元素到容量首先的Deque，推荐使用offerFirst()方法。
     *
     * Inserts the specified element at the front of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an {@code IllegalStateException} if no space is currently
     * available.  When using a capacity-restricted deque, it is generally
     * preferable to use method {@link #offerFirst}.
     *
     * @param e the element to add
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    void addFirst(E e);

    /**
     * 在Deque的尾部插入元素，如果容量不够，抛出IllegalStateException。
     * 对于容量受限的Deque，推荐使用offerLast()方法。
     *
     * Inserts the specified element at the end of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an {@code IllegalStateException} if no space is currently
     * available.  When using a capacity-restricted deque, it is generally
     * preferable to use method {@link #offerLast}.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    void addLast(E e);

    /**
     * 在Deque头部插入一个元素。
     *
     * Inserts the specified element at the front of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * this method is generally preferable to the {@link #addFirst} method,
     * which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    boolean offerFirst(E e);

    /**
     * 在Deque的尾部插入一个元素。
     *
     * Inserts the specified element at the end of this deque unless it would
     * violate capacity restrictions.  When using a capacity-restricted deque,
     * this method is generally preferable to the {@link #addLast} method,
     * which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    boolean offerLast(E e);

    /**
     * 返回并删除Deque的第一个元素。
     * 这个方法和pollFirst()不一样的地方在于如果Deque为空，会抛出异常。
     *
     * Retrieves and removes the first element of this deque.  This method
     * differs from {@link #pollFirst pollFirst} only in that it throws an
     * exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E removeFirst();

    /**
     * 返回并移除Deque最后一个元素。
     * 这个方法和pollLast不一样的地方在于，当Deque为空的时候，该方法抛出异常。
     *
     * Retrieves and removes the last element of this deque.  This method
     * differs from {@link #pollLast pollLast} only in that it throws an
     * exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E removeLast();

    /**
     * 返回并移除第一个元素。
     *
     * Retrieves and removes the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    E pollFirst();

    /**
     * 返回并移除最后一个元素。
     *
     * Retrieves and removes the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    E pollLast();

    /**
     * 返回第一个元素。
     *
     * Retrieves, but does not remove, the first element of this deque.
     *
     * 这个方法和peekFirst方法不同在于，当Deque为空的时候，该方法会抛出异常。
     *
     * This method differs from {@link #peekFirst peekFirst} only in that it
     * throws an exception if this deque is empty.
     *
     * @return the head of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E getFirst();

    /**
     * 返回最后一个元素
     * 该方法和peekLast不同在于，当Deque为空的时候，该方法抛出异常。
     *
     * Retrieves, but does not remove, the last element of this deque.
     * This method differs from {@link #peekLast peekLast} only in that it
     * throws an exception if this deque is empty.
     *
     * @return the tail of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E getLast();

    /**
     * 返回第一个元素。
     *
     * Retrieves, but does not remove, the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the head of this deque, or {@code null} if this deque is empty
     */
    E peekFirst();

    /**
     * 返回最后一个元素。
     *
     * Retrieves, but does not remove, the last element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tail of this deque, or {@code null} if this deque is empty
     */
    E peekLast();

    /**
     * 删除第一次痴线的该元素。
     *
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contain the element, it is unchanged.
     * More formally, removes the first element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>
     * (if such an element exists).
     * Returns {@code true} if this deque contained the specified element
     * (or equivalently, if this deque changed as a result of the call).
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException if the class of the specified element
     *         is incompatible with this deque
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean removeFirstOccurrence(Object o);

    /**
     * 删除该元素最后一次出现。
     *
     * Removes the last occurrence of the specified element from this deque.
     * If the deque does not contain the element, it is unchanged.
     * More formally, removes the last element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>
     * (if such an element exists).
     * Returns {@code true} if this deque contained the specified element
     * (or equivalently, if this deque changed as a result of the call).
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException if the class of the specified element
     *         is incompatible with this deque
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean removeLastOccurrence(Object o);

    // *** Queue methods ***Queue接口方法

    /**
     * 在Deque中插入元素（尾部插入）
     * 插入成功返回true，如果超出Deque容量，抛出IllegalStateException。
     * 使用容量受限受限的Deque的时候，推荐市容offer()方法。
     *
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an
     * {@code IllegalStateException} if no space is currently available.
     * When using a capacity-restricted deque, it is generally preferable to
     * use {@link #offer(Object) offer}.
     *
     * add()方法和addLast()方法等同
     *
     * <p>This method is equivalent to {@link #addLast}.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    boolean add(E e);

    /**
     * 向Deque尾部添加元素
     *
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and {@code false} if no space is currently
     * available.  When using a capacity-restricted deque, this method is
     * generally preferable to the {@link #add} method, which can fail to
     * insert an element only by throwing an exception.
     *
     * <p>This method is equivalent to {@link #offerLast}.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    boolean offer(E e);

    /**
     * 返回并移除Deque头部的元素
     *
     * Retrieves and removes the head of the queue represented by this deque
     * (in other words, the first element of this deque).
     *
     * remove()和poll()不一样的地方在于当Deque为空的时候，remove()抛出异常。
     *
     * This method differs from {@link #poll poll} only in that it throws an
     * exception if this deque is empty.
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the head of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E remove();

    /**
     * 返回并移除Deque头部的元素。
     *
     * Retrieves and removes the head of the queue represented by this deque
     * (in other words, the first element of this deque), or returns
     * {@code null} if this deque is empty.
     *
     * poll()方法和pollFirst()方法等同。
     *
     * <p>This method is equivalent to {@link #pollFirst()}.
     *
     * @return the first element of this deque, or {@code null} if
     *         this deque is empty
     */
    E poll();

    /**
     * 返回而不移除头部第一个元素。
     *
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque (in other words, the first element of this deque).
     * This method differs from {@link #peek peek} only in that it throws an
     * exception if this deque is empty.
     *
     * 等同getFirst()方法。
     *
     * <p>This method is equivalent to {@link #getFirst()}.
     *
     * @return the head of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E element();

    /**
     * 返回而不移除Deque头部第一个元素。
     *
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque (in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * 和peekFirst()方法等同。
     *
     * <p>This method is equivalent to {@link #peekFirst()}.
     *
     * @return the head of the queue represented by this deque, or
     *         {@code null} if this deque is empty
     */
    E peek();


    // *** Stack methods ***栈方法

    /**
     * 向Deque中加入一个元素（压栈）
     * 在Deque作为栈的栈顶压入元素。
     *
     * Pushes an element onto the stack represented by this deque (in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * 和addFirst()方法等同
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this deque
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this deque
     */
    void push(E e);

    /**
     * 弹出栈顶元素（取出并移除）
     *
     * Pops an element from the stack represented by this deque.  In other
     * words, removes and returns the first element of this deque.
     *
     * 和removeFirst()方法相同
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return the element at the front of this deque (which is the top
     *         of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    E pop();


    // *** Collection methods ***集合方法

    /**
     * 移除第一次出现的O元素
     *
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contain the element, it is unchanged.
     * More formally, removes the first element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>
     * (if such an element exists).
     * Returns {@code true} if this deque contained the specified element
     * (or equivalently, if this deque changed as a result of the call).
     *
     * 和removeFirstOccurrence(Object)等同
     *
     * <p>This method is equivalent to {@link #removeFirstOccurrence(Object)}.
     *
     * @param o element to be removed from this deque, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException if the class of the specified element
     *         is incompatible with this deque
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean remove(Object o);

    /**
     * 检查是否包含指定元素
     *
     * Returns {@code true} if this deque contains the specified element.
     * More formally, returns {@code true} if and only if this deque contains
     * at least one element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this deque is to be tested
     * @return {@code true} if this deque contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this deque
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean contains(Object o);

    /**
     * 返回Deque中元素个数
     *
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    public int size();

    /**
     * 返回元素迭代器
     *
     * Returns an iterator over the elements in this deque in proper sequence.
     * The elements will be returned in order from first (head) to last (tail).
     *
     * @return an iterator over the elements in this deque in proper sequence
     */
    Iterator<E> iterator();

    /**
     * 返回降序的迭代器
     *
     * Returns an iterator over the elements in this deque in reverse
     * sequential order.  The elements will be returned in order from
     * last (tail) to first (head).
     *
     * @return an iterator over the elements in this deque in reverse
     * sequence
     */
    Iterator<E> descendingIterator();

}
