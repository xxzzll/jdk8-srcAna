/*
 * Copyright (c) 1998, 2013, Oracle and/or its affiliates. All rights reserved.
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

package java.util;

/**
 * SortedSet接口为集合中的元素提供了一种整体的排序方法。
 * 元素的排序方式采用元素自身Comparable接口定义的比较方法排序，或者可以自定义Comparator用于比较排序。
 * Comparable或者Comparator在SortedSet定义的时候确定。SortedSet的iterator会按照升序的顺序遍历SortedSet。
 * SortedSet接口与SortedMap接口类似。
 *
 * A {@link Set} that further provides a <i>total ordering</i> on its elements.
 * The elements are ordered using their {@linkplain Comparable natural
 * ordering}, or by a {@link Comparator} typically provided at sorted
 * set creation time.  The set's iterator will traverse the set in
 * ascending element order. Several additional operations are provided
 * to take advantage of the ordering.  (This interface is the set
 * analogue of {@link SortedMap}.)
 *
 * 插入SortedSet中的元素必须实现Comparable接口，并重写compareTo()方法，或者有自定义的comparator。
 * 并且，所有的元素都要具有互相可比性，即：e1.compareTo(e2)或者comparator.compare(e1, e2)不会
 * 抛出ClassCastException。
 * 如果破坏了这个限制，调用的方法或者构造方法会抛出ClassCastException异常。
 *
 * <p>All elements inserted into a sorted set must implement the <tt>Comparable</tt>
 * interface (or be accepted by the specified comparator).  Furthermore, all
 * such elements must be <i>mutually comparable</i>: <tt>e1.compareTo(e2)</tt>
 * (or <tt>comparator.compare(e1, e2)</tt>) must not throw a
 * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and <tt>e2</tt> in
 * the sorted set.  Attempts to violate this restriction will cause the
 * offending method or constructor invocation to throw a
 * <tt>ClassCastException</tt>.
 *
 * 注意：SortedSet保持的排序需要鱼equals()方法保持一致（Comparable接口和Comparator接口有“与equals()方法一直的详细描述”）
 * 因为Set接口定义了equals（）方法用于比较两个Set是否相同，但是SortedSet的所有元素的比较使用compareTo（）方法或者compare()方法
 * 所以，两个元素相等，从SortedSet的角度看，也相等。
 * SortedSet具有这种性质，即使需要与equals()方法保持一致，这种性质不能满足Set接口的一些规定。
 *
 * <p>Note that the ordering maintained by a sorted set (whether or not an
 * explicit comparator is provided) must be <i>consistent with equals</i> if
 * the sorted set is to correctly implement the <tt>Set</tt> interface.  (See
 * the <tt>Comparable</tt> interface or <tt>Comparator</tt> interface for a
 * precise definition of <i>consistent with equals</i>.)  This is so because
 * the <tt>Set</tt> interface is defined in terms of the <tt>equals</tt>
 * operation, but a sorted set performs all element comparisons using its
 * <tt>compareTo</tt> (or <tt>compare</tt>) method, so two elements that are
 * deemed equal by this method are, from the standpoint of the sorted set,
 * equal.  The behavior of a sorted set <i>is</i> well-defined even if its
 * ordering is inconsistent with equals; it just fails to obey the general
 * contract of the <tt>Set</tt> interface.
 *
 * 所有的SortedSet都需要满足4个标准的构造韩方法：
 * 1）无参构造方法，返回一个按照自然排序的SortedSet；
 * 2）一个带有Comparator参数的构造方法，返回一个带有指定比较器的SortedSet；
 * 3）带有一个Collection类型参数的构造方法，返回一个包含参数中所有元素的SortedSet；
 * 4）带有一个SortedSet类型参数的构造方法，返回一个包含参数中所有元素和顺序的SortedSet。
 * 没有办法去增强这种构造方法的实现，因为接口不能包含构造方法。
 *
 * <p>All general-purpose sorted set implementation classes should
 * provide four "standard" constructors: 1) A void (no arguments)
 * constructor, which creates an empty sorted set sorted according to
 * the natural ordering of its elements.  2) A constructor with a
 * single argument of type <tt>Comparator</tt>, which creates an empty
 * sorted set sorted according to the specified comparator.  3) A
 * constructor with a single argument of type <tt>Collection</tt>,
 * which creates a new sorted set with the same elements as its
 * argument, sorted according to the natural ordering of the elements.
 * 4) A constructor with a single argument of type <tt>SortedSet</tt>,
 * which creates a new sorted set with the same elements and the same
 * ordering as the input sorted set.  There is no way to enforce this
 * recommendation, as interfaces cannot contain constructors.
 *
 * 注意：有几个方法返回有范围限制子集合，这些范围是半开的。
 *
 * <p>Note: several methods return subsets with restricted ranges.
 * Such ranges are <i>half-open</i>, that is, they include their low
 * endpoint but not their high endpoint (where applicable).
 * If you need a <i>closed range</i> (which includes both endpoints), and
 * the element type allows for calculation of the successor of a given
 * value, merely request the subrange from <tt>lowEndpoint</tt> to
 * <tt>successor(highEndpoint)</tt>.  For example, suppose that <tt>s</tt>
 * is a sorted set of strings.  The following idiom obtains a view
 * containing all of the strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, inclusive:<pre>
 *   SortedSet&lt;String&gt; sub = s.subSet(low, high+"\0");</pre>
 *
 * A similar technique can be used to generate an <i>open range</i> (which
 * contains neither endpoint).  The following idiom obtains a view
 * containing all of the Strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, exclusive:<pre>
 *   SortedSet&lt;String&gt; sub = s.subSet(low+"\0", high);</pre>
 *
 * SortedSet属于Java Collections Framework的一员。
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author  Josh Bloch
 * @see Set
 * @see TreeSet
 * @see SortedMap
 * @see Collection
 * @see Comparable
 * @see Comparator
 * @see ClassCastException
 * @since 1.2
 */

/**
 * SortedSet顾名思义就是有序的集合，要实现集合的有序，需要对集合的元素进行比较
 * Java中比较的实现可以实现Comparable接口，重写compareTo()方法；
 * 或者实现Comparator接口，重写compare()方法；
 * Object类的equals（）方法也是用于元素的比较，但是元素重写hashCode()方法和equals()方法之后只能比较元素是否相同
 * 所以，SortedSet中的元素一定要实现可比较的方法。
 *
 */
public interface SortedSet<E> extends Set<E> {
    /**
     * 如果comparator()方法返回为null，则使用默认的比较方法，
     * 字符按字符表顺序排序，数字按大小排序
     *
     * Returns the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order the elements in this set,
     *         or <tt>null</tt> if this set uses the natural ordering
     *         of its elements
     */
    Comparator<? super E> comparator();

    /**
     * 返回一个子集合
     * 左闭右开[fromElement,toElement)
     * 如果fromElement与toElement相等，返回空集合
     * 返回的集合元素的存储还是在之前的集合，改变返回的子集会影响之前的元素
     * 改变之前的集合也会影响子集
     *
     * Returns a view of the portion of this set whose elements range
     * from <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>,
     * exclusive.  (If <tt>fromElement</tt> and <tt>toElement</tt> are
     * equal, the returned set is empty.)  The returned set is backed
     * by this set, so changes in the returned set are reflected in
     * this set, and vice-versa.  The returned set supports all
     * optional set operations that this set supports.
     *
     * 当向子集中插入超过的范围的值的时候，会抛出IllegalArgumentException。
     *
     * <p>The returned set will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint (inclusive) of the returned set
     * @param toElement high endpoint (exclusive) of the returned set
     * @return a view of the portion of this set whose elements range from
     *         <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>, exclusive
     * @throws ClassCastException if <tt>fromElement</tt> and
     *         <tt>toElement</tt> cannot be compared to one another using this
     *         set's comparator (or, if the set has no comparator, using
     *         natural ordering).  Implementations may, but are not required
     *         to, throw this exception if <tt>fromElement</tt> or
     *         <tt>toElement</tt> cannot be compared to elements currently in
     *         the set.
     * @throws NullPointerException if <tt>fromElement</tt> or
     *         <tt>toElement</tt> is null and this set does not permit null
     *         elements
     * @throws IllegalArgumentException if <tt>fromElement</tt> is
     *         greater than <tt>toElement</tt>; or if this set itself
     *         has a restricted range, and <tt>fromElement</tt> or
     *         <tt>toElement</tt> lies outside the bounds of the range
     */
    SortedSet<E> subSet(E fromElement, E toElement);

    /**
     * 返回头部子集（toElement之前的集合，不包括toElement）
     *
     * Returns a view of the portion of this set whose elements are
     * strictly less than <tt>toElement</tt>.  The returned set is
     * backed by this set, so changes in the returned set are
     * reflected in this set, and vice-versa.  The returned set
     * supports all optional set operations that this set supports.
     *
     * <p>The returned set will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param toElement high endpoint (exclusive) of the returned set
     * @return a view of the portion of this set whose elements are strictly
     *         less than <tt>toElement</tt>
     * @throws ClassCastException if <tt>toElement</tt> is not compatible
     *         with this set's comparator (or, if the set has no comparator,
     *         if <tt>toElement</tt> does not implement {@link Comparable}).
     *         Implementations may, but are not required to, throw this
     *         exception if <tt>toElement</tt> cannot be compared to elements
     *         currently in the set.
     * @throws NullPointerException if <tt>toElement</tt> is null and
     *         this set does not permit null elements
     * @throws IllegalArgumentException if this set itself has a
     *         restricted range, and <tt>toElement</tt> lies outside the
     *         bounds of the range
     */
    SortedSet<E> headSet(E toElement);

    /**
     * 返回尾部子集（fromElement至集合末尾，包括fromElement）
     *
     * Returns a view of the portion of this set whose elements are
     * greater than or equal to <tt>fromElement</tt>.  The returned
     * set is backed by this set, so changes in the returned set are
     * reflected in this set, and vice-versa.  The returned set
     * supports all optional set operations that this set supports.
     *
     * <p>The returned set will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint (inclusive) of the returned set
     * @return a view of the portion of this set whose elements are greater
     *         than or equal to <tt>fromElement</tt>
     * @throws ClassCastException if <tt>fromElement</tt> is not compatible
     *         with this set's comparator (or, if the set has no comparator,
     *         if <tt>fromElement</tt> does not implement {@link Comparable}).
     *         Implementations may, but are not required to, throw this
     *         exception if <tt>fromElement</tt> cannot be compared to elements
     *         currently in the set.
     * @throws NullPointerException if <tt>fromElement</tt> is null
     *         and this set does not permit null elements
     * @throws IllegalArgumentException if this set itself has a
     *         restricted range, and <tt>fromElement</tt> lies outside the
     *         bounds of the range
     */
    SortedSet<E> tailSet(E fromElement);

    /**
     * 返回第一个元素
     *
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    E first();

    /**
     * 返回最后一个元素
     *
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    E last();

    /**
     * 创建集合的一个Spliterator，用于集合遍历
     *
     * Creates a {@code Spliterator} over the elements in this sorted set.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#DISTINCT},
     * {@link Spliterator#SORTED} and {@link Spliterator#ORDERED}.
     * Implementations should document the reporting of additional
     * characteristic values.
     *
     * <p>The spliterator's comparator (see
     * {@link java.util.Spliterator#getComparator()}) must be {@code null} if
     * the sorted set's comparator (see {@link #comparator()}) is {@code null}.
     * Otherwise, the spliterator's comparator must be the same as or impose the
     * same total ordering as the sorted set's comparator.
     *
     * @implSpec
     * The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the sorted set's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the set's iterator.  The
     * spliterator's comparator is the same as the sorted set's comparator.
     * <p>
     * The created {@code Spliterator} additionally reports
     * {@link Spliterator#SIZED}.
     *
     * @implNote
     * The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     *
     * @return a {@code Spliterator} over the elements in this sorted set
     * @since 1.8
     */
    @Override
    default Spliterator<E> spliterator() {
        return new Spliterators.IteratorSpliterator<E>(
                this, Spliterator.DISTINCT | Spliterator.SORTED | Spliterator.ORDERED) {
            @Override
            public Comparator<? super E> getComparator() {
                return SortedSet.this.comparator();
            }
        };
    }
}
