<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- code.jsp -->


<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>java.util: Hashtable.java</title>
</head>
<body style="padding:0 0 0 0;margin:0 0 0 0">
<div style="width:100%;background-color:#ddeeff;border:1px solid #ccddee;margin:0 0 0 0;padding:2px 2px 2px 2px">
<div style="float:right"><a href="http://del.icio.us/post" onclick="window.open('http://del.icio.us/post?v=4&noui&jump=close&url='+encodeURIComponent(location.href)+'&title='+encodeURIComponent(document.title), 'delicious','toolbar=no,width=700,height=400'); return false;"><img src="http://images.del.icio.us/static/img/delicious.small.gif" border=0> Save This Page</a></div>
<a href="/">Home</a> &#187; <a href="/projects/openjdk-7-java.html">openjdk-7</a> &#187; java &#187;  <a href='/docs/api/java/util/package-index.html'>util</a> &#187; 
 [<a href="/docs/api/java/util/Hashtable.html">javadoc</a> | source]
</div>
<pre>
<a name='1'>    1 &nbsp; /*
    2 &nbsp;  * Copyright (c) 1994, 2011, Oracle and/or its affiliates. All rights reserved.
    3 &nbsp;  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
    4 &nbsp;  *
    5 &nbsp;  * This code is free software; you can redistribute it and/or modify it
    6 &nbsp;  * under the terms of the GNU General Public License version 2 only, as
    7 &nbsp;  * published by the Free Software Foundation.  Oracle designates this
    8 &nbsp;  * particular file as subject to the "Classpath" exception as provided
    9 &nbsp;  * by Oracle in the LICENSE file that accompanied this code.
   10 &nbsp;  *
<a name='11'>   11 &nbsp;  * This code is distributed in the hope that it will be useful, but WITHOUT
   12 &nbsp;  * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
   13 &nbsp;  * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
   14 &nbsp;  * version 2 for more details (a copy is included in the LICENSE file that
   15 &nbsp;  * accompanied this code).
   16 &nbsp;  *
   17 &nbsp;  * You should have received a copy of the GNU General Public License version
   18 &nbsp;  * 2 along with this work; if not, write to the Free Software Foundation,
   19 &nbsp;  * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
   20 &nbsp;  *
<a name='21'>   21 &nbsp;  * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
   22 &nbsp;  * or visit www.oracle.com if you need additional information or have any
   23 &nbsp;  * questions.
   24 &nbsp;  */
   25 &nbsp; 
   26 &nbsp; <span class='kw'>package</span> <a href=/docs/api/java/util/package-index.html>java.util</a>;
   27 &nbsp; <span class='kw'>import</span> <a href=/docs/api/java/io/package-index.html>java.io</a>;
   28 &nbsp; 
   29 &nbsp; /**
   30 &nbsp;  * This class implements a hash table, which maps keys to values. Any
<a name='31'>   31 &nbsp;  * non-&lt;code&gt;null&lt;/code&gt; object can be used as a key or as a value. &lt;p&gt;
   32 &nbsp;  *
   33 &nbsp;  * To successfully store and retrieve objects from a hashtable, the
   34 &nbsp;  * objects used as keys must implement the &lt;code&gt;hashCode&lt;/code&gt;
   35 &nbsp;  * method and the &lt;code&gt;equals&lt;/code&gt; method. &lt;p&gt;
   36 &nbsp;  *
   37 &nbsp;  * An instance of &lt;code&gt;Hashtable&lt;/code&gt; has two parameters that affect its
   38 &nbsp;  * performance: &lt;i&gt;initial capacity&lt;/i&gt; and &lt;i&gt;load factor&lt;/i&gt;.  The
   39 &nbsp;  * &lt;i&gt;capacity&lt;/i&gt; is the number of &lt;i&gt;buckets&lt;/i&gt; in the hash table, and the
   40 &nbsp;  * &lt;i&gt;initial capacity&lt;/i&gt; is simply the capacity at the time the hash table
<a name='41'>   41 &nbsp;  * is created.  Note that the hash table is &lt;i&gt;open&lt;/i&gt;: in the case of a "hash
   42 &nbsp;  * collision", a single bucket stores multiple entries, which must be searched
   43 &nbsp;  * sequentially.  The &lt;i&gt;load factor&lt;/i&gt; is a measure of how full the hash
   44 &nbsp;  * table is allowed to get before its capacity is automatically increased.
   45 &nbsp;  * The initial capacity and load factor parameters are merely hints to
   46 &nbsp;  * the implementation.  The exact details as to when and whether the rehash
   47 &nbsp;  * method is invoked are implementation-dependent.&lt;p&gt;
   48 &nbsp;  *
   49 &nbsp;  * Generally, the default load factor (.75) offers a good tradeoff between
   50 &nbsp;  * time and space costs.  Higher values decrease the space overhead but
<a name='51'>   51 &nbsp;  * increase the time cost to look up an entry (which is reflected in most
   52 &nbsp;  * &lt;tt&gt;Hashtable&lt;/tt&gt; operations, including &lt;tt&gt;get&lt;/tt&gt; and &lt;tt&gt;put&lt;/tt&gt;).&lt;p&gt;
   53 &nbsp;  *
   54 &nbsp;  * The initial capacity controls a tradeoff between wasted space and the
   55 &nbsp;  * need for &lt;code&gt;rehash&lt;/code&gt; operations, which are time-consuming.
   56 &nbsp;  * No &lt;code&gt;rehash&lt;/code&gt; operations will &lt;i&gt;ever&lt;/i&gt; occur if the initial
   57 &nbsp;  * capacity is greater than the maximum number of entries the
   58 &nbsp;  * &lt;tt&gt;Hashtable&lt;/tt&gt; will contain divided by its load factor.  However,
   59 &nbsp;  * setting the initial capacity too high can waste space.&lt;p&gt;
   60 &nbsp;  *
<a name='61'>   61 &nbsp;  * If many entries are to be made into a &lt;code&gt;Hashtable&lt;/code&gt;,
   62 &nbsp;  * creating it with a sufficiently large capacity may allow the
   63 &nbsp;  * entries to be inserted more efficiently than letting it perform
   64 &nbsp;  * automatic rehashing as needed to grow the table. &lt;p&gt;
   65 &nbsp;  *
   66 &nbsp;  * This example creates a hashtable of numbers. It uses the names of
   67 &nbsp;  * the numbers as keys:
   68 &nbsp;  * &lt;pre&gt;   {@code
   69 &nbsp;  *   Hashtable&lt;String, Integer&gt; numbers
   70 &nbsp;  *     = new Hashtable&lt;String, Integer&gt;();
<a name='71'>   71 &nbsp;  *   numbers.put("one", 1);
   72 &nbsp;  *   numbers.put("two", 2);
   73 &nbsp;  *   numbers.put("three", 3);}&lt;/pre&gt;
   74 &nbsp;  *
   75 &nbsp;  * &lt;p&gt;To retrieve a number, use the following code:
   76 &nbsp;  * &lt;pre&gt;   {@code
   77 &nbsp;  *   Integer n = numbers.get("two");
   78 &nbsp;  *   if (n != null) {
   79 &nbsp;  *     System.out.println("two = " + n);
   80 &nbsp;  *   }}&lt;/pre&gt;
<a name='81'>   81 &nbsp;  *
   82 &nbsp;  * &lt;p&gt;The iterators returned by the &lt;tt&gt;iterator&lt;/tt&gt; method of the collections
   83 &nbsp;  * returned by all of this class's "collection view methods" are
   84 &nbsp;  * &lt;em&gt;fail-fast&lt;/em&gt;: if the Hashtable is structurally modified at any time
   85 &nbsp;  * after the iterator is created, in any way except through the iterator's own
   86 &nbsp;  * &lt;tt&gt;remove&lt;/tt&gt; method, the iterator will throw a {@link
   87 &nbsp;  * ConcurrentModificationException}.  Thus, in the face of concurrent
   88 &nbsp;  * modification, the iterator fails quickly and cleanly, rather than risking
   89 &nbsp;  * arbitrary, non-deterministic behavior at an undetermined time in the future.
   90 &nbsp;  * The Enumerations returned by Hashtable's keys and elements methods are
<a name='91'>   91 &nbsp;  * &lt;em&gt;not&lt;/em&gt; fail-fast.
   92 &nbsp;  *
   93 &nbsp;  * &lt;p&gt;Note that the fail-fast behavior of an iterator cannot be guaranteed
   94 &nbsp;  * as it is, generally speaking, impossible to make any hard guarantees in the
   95 &nbsp;  * presence of unsynchronized concurrent modification.  Fail-fast iterators
   96 &nbsp;  * throw &lt;tt&gt;ConcurrentModificationException&lt;/tt&gt; on a best-effort basis.
   97 &nbsp;  * Therefore, it would be wrong to write a program that depended on this
   98 &nbsp;  * exception for its correctness: &lt;i&gt;the fail-fast behavior of iterators
   99 &nbsp;  * should be used only to detect bugs.&lt;/i&gt;
  100 &nbsp;  *
<a name='101'>  101 &nbsp;  * &lt;p&gt;As of the Java 2 platform v1.2, this class was retrofitted to
  102 &nbsp;  * implement the {@link Map} interface, making it a member of the
  103 &nbsp;  * &lt;a href="{@docRoot}/../technotes/guides/collections/index.html"&gt;
  104 &nbsp;  *
  105 &nbsp;  * Java Collections Framework&lt;/a&gt;.  Unlike the new collection
  106 &nbsp;  * implementations, {@code Hashtable} is synchronized.  If a
  107 &nbsp;  * thread-safe implementation is not needed, it is recommended to use
  108 &nbsp;  * {@link HashMap} in place of {@code Hashtable}.  If a thread-safe
  109 &nbsp;  * highly-concurrent implementation is desired, then it is recommended
  110 &nbsp;  * to use {@link java.util.concurrent.ConcurrentHashMap} in place of
<a name='111'>  111 &nbsp;  * {@code Hashtable}.
  112 &nbsp;  *
  113 &nbsp;  * @author  Arthur van Hoff
  114 &nbsp;  * @author  Josh Bloch
  115 &nbsp;  * @author  Neal Gafter
  116 &nbsp;  * @see     Object#equals(java.lang.Object)
  117 &nbsp;  * @see     Object#hashCode()
  118 &nbsp;  * @see     Hashtable#rehash()
  119 &nbsp;  * @see     Collection
  120 &nbsp;  * @see     Map
<a name='121'>  121 &nbsp;  * @see     HashMap
  122 &nbsp;  * @see     TreeMap
  123 &nbsp;  * @since JDK1.0
  124 &nbsp;  */
  125 &nbsp; public class Hashtable&lt;K,V&gt;
  126 &nbsp;     extends Dictionary&lt;K,V&gt;
  127 &nbsp;     implements Map&lt;K,V&gt;, Cloneable, java.io.Serializable {
  128 &nbsp; 
  129 &nbsp;     /**
  130 &nbsp;      * The hash table data.
<a name='131'>  131 &nbsp;      */
  132 &nbsp;     private transient Entry[] table;
  133 &nbsp; 
  134 &nbsp;     /**
  135 &nbsp;      * The total number of entries in the hash table.
  136 &nbsp;      */
  137 &nbsp;     private transient int count;
  138 &nbsp; 
  139 &nbsp;     /**
  140 &nbsp;      * The table is rehashed when its size exceeds this threshold.  (The
<a name='141'>  141 &nbsp;      * value of this field is (int)(capacity * loadFactor).)
  142 &nbsp;      *
  143 &nbsp;      * @serial
  144 &nbsp;      */
  145 &nbsp;     private int threshold;
  146 &nbsp; 
  147 &nbsp;     /**
  148 &nbsp;      * The load factor for the hashtable.
  149 &nbsp;      *
  150 &nbsp;      * @serial
<a name='151'>  151 &nbsp;      */
  152 &nbsp;     private float loadFactor;
  153 &nbsp; 
  154 &nbsp;     /**
  155 &nbsp;      * The number of times this Hashtable has been structurally modified
  156 &nbsp;      * Structural modifications are those that change the number of entries in
  157 &nbsp;      * the Hashtable or otherwise modify its internal structure (e.g.,
  158 &nbsp;      * rehash).  This field is used to make iterators on Collection-views of
  159 &nbsp;      * the Hashtable fail-fast.  (See ConcurrentModificationException).
  160 &nbsp;      */
<a name='161'>  161 &nbsp;     private transient int modCount = 0;
  162 &nbsp; 
  163 &nbsp;     /** use serialVersionUID from JDK 1.0.2 for interoperability */
  164 &nbsp;     private static final long serialVersionUID = 1421746759512286392L;
  165 &nbsp; 
  166 &nbsp;     /**
  167 &nbsp;      * Constructs a new, empty hashtable with the specified initial
  168 &nbsp;      * capacity and the specified load factor.
  169 &nbsp;      *
  170 &nbsp;      * @param      initialCapacity   the initial capacity of the hashtable.
<a name='171'>  171 &nbsp;      * @param      loadFactor        the load factor of the hashtable.
  172 &nbsp;      * @exception  IllegalArgumentException  if the initial capacity is less
  173 &nbsp;      *             than zero, or if the load factor is nonpositive.
  174 &nbsp;      */
  175 &nbsp;     public Hashtable(int initialCapacity, float loadFactor) {
  176 &nbsp;         if (initialCapacity &lt; 0)
  177 &nbsp;             throw new IllegalArgumentException("Illegal Capacity: "+
  178 &nbsp;                                                initialCapacity);
  179 &nbsp;         if (loadFactor &lt;= 0 || Float.isNaN(loadFactor))
  180 &nbsp;             throw new IllegalArgumentException("Illegal Load: "+loadFactor);
<a name='181'>  181 &nbsp; 
  182 &nbsp;         if (initialCapacity==0)
  183 &nbsp;             initialCapacity = 1;
  184 &nbsp;         this.loadFactor = loadFactor;
  185 &nbsp;         table = new Entry[initialCapacity];
  186 &nbsp;         threshold = (int)(initialCapacity * loadFactor);
  187 &nbsp;     }
  188 &nbsp; 
  189 &nbsp;     /**
  190 &nbsp;      * Constructs a new, empty hashtable with the specified initial capacity
<a name='191'>  191 &nbsp;      * and default load factor (0.75).
  192 &nbsp;      *
  193 &nbsp;      * @param     initialCapacity   the initial capacity of the hashtable.
  194 &nbsp;      * @exception IllegalArgumentException if the initial capacity is less
  195 &nbsp;      *              than zero.
  196 &nbsp;      */
  197 &nbsp;     public Hashtable(int initialCapacity) {
  198 &nbsp;         this(initialCapacity, 0.75f);
  199 &nbsp;     }
  200 &nbsp; 
<a name='201'>  201 &nbsp;     /**
  202 &nbsp;      * Constructs a new, empty hashtable with a default initial capacity (11)
  203 &nbsp;      * and load factor (0.75).
  204 &nbsp;      */
  205 &nbsp;     public Hashtable() {
  206 &nbsp;         this(11, 0.75f);
  207 &nbsp;     }
  208 &nbsp; 
  209 &nbsp;     /**
  210 &nbsp;      * Constructs a new hashtable with the same mappings as the given
<a name='211'>  211 &nbsp;      * Map.  The hashtable is created with an initial capacity sufficient to
  212 &nbsp;      * hold the mappings in the given Map and a default load factor (0.75).
  213 &nbsp;      *
  214 &nbsp;      * @param t the map whose mappings are to be placed in this map.
  215 &nbsp;      * @throws NullPointerException if the specified map is null.
  216 &nbsp;      * @since   1.2
  217 &nbsp;      */
  218 &nbsp;     public Hashtable(Map&lt;? extends K, ? extends V&gt; t) {
  219 &nbsp;         this(Math.max(2*t.size(), 11), 0.75f);
  220 &nbsp;         putAll(t);
<a name='221'>  221 &nbsp;     }
  222 &nbsp; 
  223 &nbsp;     /**
  224 &nbsp;      * Returns the number of keys in this hashtable.
  225 &nbsp;      *
  226 &nbsp;      * @return  the number of keys in this hashtable.
  227 &nbsp;      */
  228 &nbsp;     public synchronized int size() {
  229 &nbsp;         return count;
  230 &nbsp;     }
<a name='231'>  231 &nbsp; 
  232 &nbsp;     /**
  233 &nbsp;      * Tests if this hashtable maps no keys to values.
  234 &nbsp;      *
  235 &nbsp;      * @return  &lt;code&gt;true&lt;/code&gt; if this hashtable maps no keys to values;
  236 &nbsp;      *          &lt;code&gt;false&lt;/code&gt; otherwise.
  237 &nbsp;      */
  238 &nbsp;     public synchronized boolean isEmpty() {
  239 &nbsp;         return count == 0;
  240 &nbsp;     }
<a name='241'>  241 &nbsp; 
  242 &nbsp;     /**
  243 &nbsp;      * Returns an enumeration of the keys in this hashtable.
  244 &nbsp;      *
  245 &nbsp;      * @return  an enumeration of the keys in this hashtable.
  246 &nbsp;      * @see     Enumeration
  247 &nbsp;      * @see     #elements()
  248 &nbsp;      * @see     #keySet()
  249 &nbsp;      * @see     Map
  250 &nbsp;      */
<a name='251'>  251 &nbsp;     public synchronized Enumeration&lt;K&gt; keys() {
  252 &nbsp;         return this.&lt;K&gt;getEnumeration(KEYS);
  253 &nbsp;     }
  254 &nbsp; 
  255 &nbsp;     /**
  256 &nbsp;      * Returns an enumeration of the values in this hashtable.
  257 &nbsp;      * Use the Enumeration methods on the returned object to fetch the elements
  258 &nbsp;      * sequentially.
  259 &nbsp;      *
  260 &nbsp;      * @return  an enumeration of the values in this hashtable.
<a name='261'>  261 &nbsp;      * @see     java.util.Enumeration
  262 &nbsp;      * @see     #keys()
  263 &nbsp;      * @see     #values()
  264 &nbsp;      * @see     Map
  265 &nbsp;      */
  266 &nbsp;     public synchronized Enumeration&lt;V&gt; elements() {
  267 &nbsp;         return this.&lt;V&gt;getEnumeration(VALUES);
  268 &nbsp;     }
  269 &nbsp; 
  270 &nbsp;     /**
<a name='271'>  271 &nbsp;      * Tests if some key maps into the specified value in this hashtable.
  272 &nbsp;      * This operation is more expensive than the {@link #containsKey
  273 &nbsp;      * containsKey} method.
  274 &nbsp;      *
  275 &nbsp;      * &lt;p&gt;Note that this method is identical in functionality to
  276 &nbsp;      * {@link #containsValue containsValue}, (which is part of the
  277 &nbsp;      * {@link Map} interface in the collections framework).
  278 &nbsp;      *
  279 &nbsp;      * @param      value   a value to search for
  280 &nbsp;      * @return     &lt;code&gt;true&lt;/code&gt; if and only if some key maps to the
<a name='281'>  281 &nbsp;      *             &lt;code&gt;value&lt;/code&gt; argument in this hashtable as
  282 &nbsp;      *             determined by the &lt;tt&gt;equals&lt;/tt&gt; method;
  283 &nbsp;      *             &lt;code&gt;false&lt;/code&gt; otherwise.
  284 &nbsp;      * @exception  NullPointerException  if the value is &lt;code&gt;null&lt;/code&gt;
  285 &nbsp;      */
  286 &nbsp;     public synchronized boolean contains(Object value) {
  287 &nbsp;         if (value == null) {
  288 &nbsp;             throw new NullPointerException();
  289 &nbsp;         }
  290 &nbsp; 
<a name='291'>  291 &nbsp;         Entry tab[] = table;
  292 &nbsp;         for (int i = tab.length ; i-- &gt; 0 ;) {
  293 &nbsp;             for (Entry&lt;K,V&gt; e = tab[i] ; e != null ; e = e.next) {
  294 &nbsp;                 if (e.value.equals(value)) {
  295 &nbsp;                     return true;
  296 &nbsp;                 }
  297 &nbsp;             }
  298 &nbsp;         }
  299 &nbsp;         return false;
  300 &nbsp;     }
<a name='301'>  301 &nbsp; 
  302 &nbsp;     /**
  303 &nbsp;      * Returns true if this hashtable maps one or more keys to this value.
  304 &nbsp;      *
  305 &nbsp;      * &lt;p&gt;Note that this method is identical in functionality to {@link
  306 &nbsp;      * #contains contains} (which predates the {@link Map} interface).
  307 &nbsp;      *
  308 &nbsp;      * @param value value whose presence in this hashtable is to be tested
  309 &nbsp;      * @return &lt;tt&gt;true&lt;/tt&gt; if this map maps one or more keys to the
  310 &nbsp;      *         specified value
<a name='311'>  311 &nbsp;      * @throws NullPointerException  if the value is &lt;code&gt;null&lt;/code&gt;
  312 &nbsp;      * @since 1.2
  313 &nbsp;      */
  314 &nbsp;     public boolean containsValue(Object value) {
  315 &nbsp;         return contains(value);
  316 &nbsp;     }
  317 &nbsp; 
  318 &nbsp;     /**
  319 &nbsp;      * Tests if the specified object is a key in this hashtable.
  320 &nbsp;      *
<a name='321'>  321 &nbsp;      * @param   key   possible key
  322 &nbsp;      * @return  &lt;code&gt;true&lt;/code&gt; if and only if the specified object
  323 &nbsp;      *          is a key in this hashtable, as determined by the
  324 &nbsp;      *          &lt;tt&gt;equals&lt;/tt&gt; method; &lt;code&gt;false&lt;/code&gt; otherwise.
  325 &nbsp;      * @throws  NullPointerException  if the key is &lt;code&gt;null&lt;/code&gt;
  326 &nbsp;      * @see     #contains(Object)
  327 &nbsp;      */
  328 &nbsp;     public synchronized boolean containsKey(Object key) {
  329 &nbsp;         Entry tab[] = table;
  330 &nbsp;         int hash = key.hashCode();
<a name='331'>  331 &nbsp;         int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  332 &nbsp;         for (Entry&lt;K,V&gt; e = tab[index] ; e != null ; e = e.next) {
  333 &nbsp;             if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {
  334 &nbsp;                 return true;
  335 &nbsp;             }
  336 &nbsp;         }
  337 &nbsp;         return false;
  338 &nbsp;     }
  339 &nbsp; 
  340 &nbsp;     /**
<a name='341'>  341 &nbsp;      * Returns the value to which the specified key is mapped,
  342 &nbsp;      * or {@code null} if this map contains no mapping for the key.
  343 &nbsp;      *
  344 &nbsp;      * &lt;p&gt;More formally, if this map contains a mapping from a key
  345 &nbsp;      * {@code k} to a value {@code v} such that {@code (key.equals(k))},
  346 &nbsp;      * then this method returns {@code v}; otherwise it returns
  347 &nbsp;      * {@code null}.  (There can be at most one such mapping.)
  348 &nbsp;      *
  349 &nbsp;      * @param key the key whose associated value is to be returned
  350 &nbsp;      * @return the value to which the specified key is mapped, or
<a name='351'>  351 &nbsp;      *         {@code null} if this map contains no mapping for the key
  352 &nbsp;      * @throws NullPointerException if the specified key is null
  353 &nbsp;      * @see     #put(Object, Object)
  354 &nbsp;      */
  355 &nbsp;     public synchronized V get(Object key) {
  356 &nbsp;         Entry tab[] = table;
  357 &nbsp;         int hash = key.hashCode();
  358 &nbsp;         int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  359 &nbsp;         for (Entry&lt;K,V&gt; e = tab[index] ; e != null ; e = e.next) {
  360 &nbsp;             if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {
<a name='361'>  361 &nbsp;                 return e.value;
  362 &nbsp;             }
  363 &nbsp;         }
  364 &nbsp;         return null;
  365 &nbsp;     }
  366 &nbsp; 
  367 &nbsp;     /**
  368 &nbsp;      * The maximum size of array to allocate.
  369 &nbsp;      * Some VMs reserve some header words in an array.
  370 &nbsp;      * Attempts to allocate larger arrays may result in
<a name='371'>  371 &nbsp;      * OutOfMemoryError: Requested array size exceeds VM limit
  372 &nbsp;      */
  373 &nbsp;     private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  374 &nbsp; 
  375 &nbsp;     /**
  376 &nbsp;      * Increases the capacity of and internally reorganizes this
  377 &nbsp;      * hashtable, in order to accommodate and access its entries more
  378 &nbsp;      * efficiently.  This method is called automatically when the
  379 &nbsp;      * number of keys in the hashtable exceeds this hashtable's capacity
  380 &nbsp;      * and load factor.
<a name='381'>  381 &nbsp;      */
  382 &nbsp;     protected void rehash() {
  383 &nbsp;         int oldCapacity = table.length;
  384 &nbsp;         Entry[] oldMap = table;
  385 &nbsp; 
  386 &nbsp;         // overflow-conscious code
  387 &nbsp;         int newCapacity = (oldCapacity &lt;&lt; 1) + 1;
  388 &nbsp;         if (newCapacity - MAX_ARRAY_SIZE &gt; 0) {
  389 &nbsp;             if (oldCapacity == MAX_ARRAY_SIZE)
  390 &nbsp;                 // Keep running with MAX_ARRAY_SIZE buckets
<a name='391'>  391 &nbsp;                 return;
  392 &nbsp;             newCapacity = MAX_ARRAY_SIZE;
  393 &nbsp;         }
  394 &nbsp;         Entry[] newMap = new Entry[newCapacity];
  395 &nbsp; 
  396 &nbsp;         modCount++;
  397 &nbsp;         threshold = (int)(newCapacity * loadFactor);
  398 &nbsp;         table = newMap;
  399 &nbsp; 
  400 &nbsp;         for (int i = oldCapacity ; i-- &gt; 0 ;) {
<a name='401'>  401 &nbsp;             for (Entry&lt;K,V&gt; old = oldMap[i] ; old != null ; ) {
  402 &nbsp;                 Entry&lt;K,V&gt; e = old;
  403 &nbsp;                 old = old.next;
  404 &nbsp; 
  405 &nbsp;                 int index = (e.hash &amp; 0x7FFFFFFF) % newCapacity;
  406 &nbsp;                 e.next = newMap[index];
  407 &nbsp;                 newMap[index] = e;
  408 &nbsp;             }
  409 &nbsp;         }
  410 &nbsp;     }
<a name='411'>  411 &nbsp; 
  412 &nbsp;     /**
  413 &nbsp;      * Maps the specified &lt;code&gt;key&lt;/code&gt; to the specified
  414 &nbsp;      * &lt;code&gt;value&lt;/code&gt; in this hashtable. Neither the key nor the
  415 &nbsp;      * value can be &lt;code&gt;null&lt;/code&gt;. &lt;p&gt;
  416 &nbsp;      *
  417 &nbsp;      * The value can be retrieved by calling the &lt;code&gt;get&lt;/code&gt; method
  418 &nbsp;      * with a key that is equal to the original key.
  419 &nbsp;      *
  420 &nbsp;      * @param      key     the hashtable key
<a name='421'>  421 &nbsp;      * @param      value   the value
  422 &nbsp;      * @return     the previous value of the specified key in this hashtable,
  423 &nbsp;      *             or &lt;code&gt;null&lt;/code&gt; if it did not have one
  424 &nbsp;      * @exception  NullPointerException  if the key or value is
  425 &nbsp;      *               &lt;code&gt;null&lt;/code&gt;
  426 &nbsp;      * @see     Object#equals(Object)
  427 &nbsp;      * @see     #get(Object)
  428 &nbsp;      */
  429 &nbsp;     public synchronized V put(K key, V value) {
  430 &nbsp;         // Make sure the value is not null
<a name='431'>  431 &nbsp;         if (value == null) {
  432 &nbsp;             throw new NullPointerException();
  433 &nbsp;         }
  434 &nbsp; 
  435 &nbsp;         // Makes sure the key is not already in the hashtable.
  436 &nbsp;         Entry tab[] = table;
  437 &nbsp;         int hash = key.hashCode();
  438 &nbsp;         int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  439 &nbsp;         for (Entry&lt;K,V&gt; e = tab[index] ; e != null ; e = e.next) {
  440 &nbsp;             if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {
<a name='441'>  441 &nbsp;                 V old = e.value;
  442 &nbsp;                 e.value = value;
  443 &nbsp;                 return old;
  444 &nbsp;             }
  445 &nbsp;         }
  446 &nbsp; 
  447 &nbsp;         modCount++;
  448 &nbsp;         if (count &gt;= threshold) {
  449 &nbsp;             // Rehash the table if the threshold is exceeded
  450 &nbsp;             rehash();
<a name='451'>  451 &nbsp; 
  452 &nbsp;             tab = table;
  453 &nbsp;             index = (hash &amp; 0x7FFFFFFF) % tab.length;
  454 &nbsp;         }
  455 &nbsp; 
  456 &nbsp;         // Creates the new entry.
  457 &nbsp;         Entry&lt;K,V&gt; e = tab[index];
  458 &nbsp;         tab[index] = new Entry&lt;&gt;(hash, key, value, e);
  459 &nbsp;         count++;
  460 &nbsp;         return null;
<a name='461'>  461 &nbsp;     }
  462 &nbsp; 
  463 &nbsp;     /**
  464 &nbsp;      * Removes the key (and its corresponding value) from this
  465 &nbsp;      * hashtable. This method does nothing if the key is not in the hashtable.
  466 &nbsp;      *
  467 &nbsp;      * @param   key   the key that needs to be removed
  468 &nbsp;      * @return  the value to which the key had been mapped in this hashtable,
  469 &nbsp;      *          or &lt;code&gt;null&lt;/code&gt; if the key did not have a mapping
  470 &nbsp;      * @throws  NullPointerException  if the key is &lt;code&gt;null&lt;/code&gt;
<a name='471'>  471 &nbsp;      */
  472 &nbsp;     public synchronized V remove(Object key) {
  473 &nbsp;         Entry tab[] = table;
  474 &nbsp;         int hash = key.hashCode();
  475 &nbsp;         int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  476 &nbsp;         for (Entry&lt;K,V&gt; e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
  477 &nbsp;             if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {
  478 &nbsp;                 modCount++;
  479 &nbsp;                 if (prev != null) {
  480 &nbsp;                     prev.next = e.next;
<a name='481'>  481 &nbsp;                 } else {
  482 &nbsp;                     tab[index] = e.next;
  483 &nbsp;                 }
  484 &nbsp;                 count--;
  485 &nbsp;                 V oldValue = e.value;
  486 &nbsp;                 e.value = null;
  487 &nbsp;                 return oldValue;
  488 &nbsp;             }
  489 &nbsp;         }
  490 &nbsp;         return null;
<a name='491'>  491 &nbsp;     }
  492 &nbsp; 
  493 &nbsp;     /**
  494 &nbsp;      * Copies all of the mappings from the specified map to this hashtable.
  495 &nbsp;      * These mappings will replace any mappings that this hashtable had for any
  496 &nbsp;      * of the keys currently in the specified map.
  497 &nbsp;      *
  498 &nbsp;      * @param t mappings to be stored in this map
  499 &nbsp;      * @throws NullPointerException if the specified map is null
  500 &nbsp;      * @since 1.2
<a name='501'>  501 &nbsp;      */
  502 &nbsp;     public synchronized void putAll(Map&lt;? extends K, ? extends V&gt; t) {
  503 &nbsp;         for (Map.Entry&lt;? extends K, ? extends V&gt; e : t.entrySet())
  504 &nbsp;             put(e.getKey(), e.getValue());
  505 &nbsp;     }
  506 &nbsp; 
  507 &nbsp;     /**
  508 &nbsp;      * Clears this hashtable so that it contains no keys.
  509 &nbsp;      */
  510 &nbsp;     public synchronized void clear() {
<a name='511'>  511 &nbsp;         Entry tab[] = table;
  512 &nbsp;         modCount++;
  513 &nbsp;         for (int index = tab.length; --index &gt;= 0; )
  514 &nbsp;             tab[index] = null;
  515 &nbsp;         count = 0;
  516 &nbsp;     }
  517 &nbsp; 
  518 &nbsp;     /**
  519 &nbsp;      * Creates a shallow copy of this hashtable. All the structure of the
  520 &nbsp;      * hashtable itself is copied, but the keys and values are not cloned.
<a name='521'>  521 &nbsp;      * This is a relatively expensive operation.
  522 &nbsp;      *
  523 &nbsp;      * @return  a clone of the hashtable
  524 &nbsp;      */
  525 &nbsp;     public synchronized Object clone() {
  526 &nbsp;         try {
  527 &nbsp;             Hashtable&lt;K,V&gt; t = (Hashtable&lt;K,V&gt;) super.clone();
  528 &nbsp;             t.table = new Entry[table.length];
  529 &nbsp;             for (int i = table.length ; i-- &gt; 0 ; ) {
  530 &nbsp;                 t.table[i] = (table[i] != null)
<a name='531'>  531 &nbsp;                     ? (Entry&lt;K,V&gt;) table[i].clone() : null;
  532 &nbsp;             }
  533 &nbsp;             t.keySet = null;
  534 &nbsp;             t.entrySet = null;
  535 &nbsp;             t.values = null;
  536 &nbsp;             t.modCount = 0;
  537 &nbsp;             return t;
  538 &nbsp;         } catch (CloneNotSupportedException e) {
  539 &nbsp;             // this shouldn't happen, since we are Cloneable
  540 &nbsp;             throw new InternalError();
<a name='541'>  541 &nbsp;         }
  542 &nbsp;     }
  543 &nbsp; 
  544 &nbsp;     /**
  545 &nbsp;      * Returns a string representation of this &lt;tt&gt;Hashtable&lt;/tt&gt; object
  546 &nbsp;      * in the form of a set of entries, enclosed in braces and separated
  547 &nbsp;      * by the ASCII characters "&lt;tt&gt;,&amp;nbsp;&lt;/tt&gt;" (comma and space). Each
  548 &nbsp;      * entry is rendered as the key, an equals sign &lt;tt&gt;=&lt;/tt&gt;, and the
  549 &nbsp;      * associated element, where the &lt;tt&gt;toString&lt;/tt&gt; method is used to
  550 &nbsp;      * convert the key and element to strings.
<a name='551'>  551 &nbsp;      *
  552 &nbsp;      * @return  a string representation of this hashtable
  553 &nbsp;      */
  554 &nbsp;     public synchronized String toString() {
  555 &nbsp;         int max = size() - 1;
  556 &nbsp;         if (max == -1)
  557 &nbsp;             return "{}";
  558 &nbsp; 
  559 &nbsp;         StringBuilder sb = new StringBuilder();
  560 &nbsp;         Iterator&lt;Map.Entry&lt;K,V&gt;&gt; it = entrySet().iterator();
<a name='561'>  561 &nbsp; 
  562 &nbsp;         sb.append('{');
  563 &nbsp;         for (int i = 0; ; i++) {
  564 &nbsp;             Map.Entry&lt;K,V&gt; e = it.next();
  565 &nbsp;             K key = e.getKey();
  566 &nbsp;             V value = e.getValue();
  567 &nbsp;             sb.append(key   == this ? "(this Map)" : key.toString());
  568 &nbsp;             sb.append('=');
  569 &nbsp;             sb.append(value == this ? "(this Map)" : value.toString());
  570 &nbsp; 
<a name='571'>  571 &nbsp;             if (i == max)
  572 &nbsp;                 return sb.append('}').toString();
  573 &nbsp;             sb.append(", ");
  574 &nbsp;         }
  575 &nbsp;     }
  576 &nbsp; 
  577 &nbsp; 
  578 &nbsp;     private &lt;T&gt; Enumeration&lt;T&gt; getEnumeration(int type) {
  579 &nbsp;         if (count == 0) {
  580 &nbsp;             return Collections.emptyEnumeration();
<a name='581'>  581 &nbsp;         } else {
  582 &nbsp;             return new Enumerator&lt;&gt;(type, false);
  583 &nbsp;         }
  584 &nbsp;     }
  585 &nbsp; 
  586 &nbsp;     private &lt;T&gt; Iterator&lt;T&gt; getIterator(int type) {
  587 &nbsp;         if (count == 0) {
  588 &nbsp;             return Collections.emptyIterator();
  589 &nbsp;         } else {
  590 &nbsp;             return new Enumerator&lt;&gt;(type, true);
<a name='591'>  591 &nbsp;         }
  592 &nbsp;     }
  593 &nbsp; 
  594 &nbsp;     // Views
  595 &nbsp; 
  596 &nbsp;     /**
  597 &nbsp;      * Each of these fields are initialized to contain an instance of the
  598 &nbsp;      * appropriate view the first time this view is requested.  The views are
  599 &nbsp;      * stateless, so there's no reason to create more than one of each.
  600 &nbsp;      */
<a name='601'>  601 &nbsp;     private transient volatile Set&lt;K&gt; keySet = null;
  602 &nbsp;     private transient volatile Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet = null;
  603 &nbsp;     private transient volatile Collection&lt;V&gt; values = null;
  604 &nbsp; 
  605 &nbsp;     /**
  606 &nbsp;      * Returns a {@link Set} view of the keys contained in this map.
  607 &nbsp;      * The set is backed by the map, so changes to the map are
  608 &nbsp;      * reflected in the set, and vice-versa.  If the map is modified
  609 &nbsp;      * while an iteration over the set is in progress (except through
  610 &nbsp;      * the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation), the results of
<a name='611'>  611 &nbsp;      * the iteration are undefined.  The set supports element removal,
  612 &nbsp;      * which removes the corresponding mapping from the map, via the
  613 &nbsp;      * &lt;tt&gt;Iterator.remove&lt;/tt&gt;, &lt;tt&gt;Set.remove&lt;/tt&gt;,
  614 &nbsp;      * &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt;, and &lt;tt&gt;clear&lt;/tt&gt;
  615 &nbsp;      * operations.  It does not support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt;
  616 &nbsp;      * operations.
  617 &nbsp;      *
  618 &nbsp;      * @since 1.2
  619 &nbsp;      */
  620 &nbsp;     public Set&lt;K&gt; keySet() {
<a name='621'>  621 &nbsp;         if (keySet == null)
  622 &nbsp;             keySet = Collections.synchronizedSet(new KeySet(), this);
  623 &nbsp;         return keySet;
  624 &nbsp;     }
  625 &nbsp; 
  626 &nbsp;     private class KeySet extends AbstractSet&lt;K&gt; {
  627 &nbsp;         public Iterator&lt;K&gt; iterator() {
  628 &nbsp;             return getIterator(KEYS);
  629 &nbsp;         }
  630 &nbsp;         public int size() {
<a name='631'>  631 &nbsp;             return count;
  632 &nbsp;         }
  633 &nbsp;         public boolean contains(Object o) {
  634 &nbsp;             return containsKey(o);
  635 &nbsp;         }
  636 &nbsp;         public boolean remove(Object o) {
  637 &nbsp;             return Hashtable.this.remove(o) != null;
  638 &nbsp;         }
  639 &nbsp;         public void clear() {
  640 &nbsp;             Hashtable.this.clear();
<a name='641'>  641 &nbsp;         }
  642 &nbsp;     }
  643 &nbsp; 
  644 &nbsp;     /**
  645 &nbsp;      * Returns a {@link Set} view of the mappings contained in this map.
  646 &nbsp;      * The set is backed by the map, so changes to the map are
  647 &nbsp;      * reflected in the set, and vice-versa.  If the map is modified
  648 &nbsp;      * while an iteration over the set is in progress (except through
  649 &nbsp;      * the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation, or through the
  650 &nbsp;      * &lt;tt&gt;setValue&lt;/tt&gt; operation on a map entry returned by the
<a name='651'>  651 &nbsp;      * iterator) the results of the iteration are undefined.  The set
  652 &nbsp;      * supports element removal, which removes the corresponding
  653 &nbsp;      * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,
  654 &nbsp;      * &lt;tt&gt;Set.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;, &lt;tt&gt;retainAll&lt;/tt&gt; and
  655 &nbsp;      * &lt;tt&gt;clear&lt;/tt&gt; operations.  It does not support the
  656 &nbsp;      * &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; operations.
  657 &nbsp;      *
  658 &nbsp;      * @since 1.2
  659 &nbsp;      */
  660 &nbsp;     public Set&lt;Map.Entry&lt;K,V&gt;&gt; entrySet() {
<a name='661'>  661 &nbsp;         if (entrySet==null)
  662 &nbsp;             entrySet = Collections.synchronizedSet(new EntrySet(), this);
  663 &nbsp;         return entrySet;
  664 &nbsp;     }
  665 &nbsp; 
  666 &nbsp;     private class EntrySet extends AbstractSet&lt;Map.Entry&lt;K,V&gt;&gt; {
  667 &nbsp;         public Iterator&lt;Map.Entry&lt;K,V&gt;&gt; iterator() {
  668 &nbsp;             return getIterator(ENTRIES);
  669 &nbsp;         }
  670 &nbsp; 
<a name='671'>  671 &nbsp;         public boolean add(Map.Entry&lt;K,V&gt; o) {
  672 &nbsp;             return super.add(o);
  673 &nbsp;         }
  674 &nbsp; 
  675 &nbsp;         public boolean contains(Object o) {
  676 &nbsp;             if (!(o instanceof Map.Entry))
  677 &nbsp;                 return false;
  678 &nbsp;             Map.Entry entry = (Map.Entry)o;
  679 &nbsp;             Object key = entry.getKey();
  680 &nbsp;             Entry[] tab = table;
<a name='681'>  681 &nbsp;             int hash = key.hashCode();
  682 &nbsp;             int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  683 &nbsp; 
  684 &nbsp;             for (Entry e = tab[index]; e != null; e = e.next)
  685 &nbsp;                 if (e.hash==hash &amp;&amp; e.equals(entry))
  686 &nbsp;                     return true;
  687 &nbsp;             return false;
  688 &nbsp;         }
  689 &nbsp; 
  690 &nbsp;         public boolean remove(Object o) {
<a name='691'>  691 &nbsp;             if (!(o instanceof Map.Entry))
  692 &nbsp;                 return false;
  693 &nbsp;             Map.Entry&lt;K,V&gt; entry = (Map.Entry&lt;K,V&gt;) o;
  694 &nbsp;             K key = entry.getKey();
  695 &nbsp;             Entry[] tab = table;
  696 &nbsp;             int hash = key.hashCode();
  697 &nbsp;             int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  698 &nbsp; 
  699 &nbsp;             for (Entry&lt;K,V&gt; e = tab[index], prev = null; e != null;
  700 &nbsp;                  prev = e, e = e.next) {
<a name='701'>  701 &nbsp;                 if (e.hash==hash &amp;&amp; e.equals(entry)) {
  702 &nbsp;                     modCount++;
  703 &nbsp;                     if (prev != null)
  704 &nbsp;                         prev.next = e.next;
  705 &nbsp;                     else
  706 &nbsp;                         tab[index] = e.next;
  707 &nbsp; 
  708 &nbsp;                     count--;
  709 &nbsp;                     e.value = null;
  710 &nbsp;                     return true;
<a name='711'>  711 &nbsp;                 }
  712 &nbsp;             }
  713 &nbsp;             return false;
  714 &nbsp;         }
  715 &nbsp; 
  716 &nbsp;         public int size() {
  717 &nbsp;             return count;
  718 &nbsp;         }
  719 &nbsp; 
  720 &nbsp;         public void clear() {
<a name='721'>  721 &nbsp;             Hashtable.this.clear();
  722 &nbsp;         }
  723 &nbsp;     }
  724 &nbsp; 
  725 &nbsp;     /**
  726 &nbsp;      * Returns a {@link Collection} view of the values contained in this map.
  727 &nbsp;      * The collection is backed by the map, so changes to the map are
  728 &nbsp;      * reflected in the collection, and vice-versa.  If the map is
  729 &nbsp;      * modified while an iteration over the collection is in progress
  730 &nbsp;      * (except through the iterator's own &lt;tt&gt;remove&lt;/tt&gt; operation),
<a name='731'>  731 &nbsp;      * the results of the iteration are undefined.  The collection
  732 &nbsp;      * supports element removal, which removes the corresponding
  733 &nbsp;      * mapping from the map, via the &lt;tt&gt;Iterator.remove&lt;/tt&gt;,
  734 &nbsp;      * &lt;tt&gt;Collection.remove&lt;/tt&gt;, &lt;tt&gt;removeAll&lt;/tt&gt;,
  735 &nbsp;      * &lt;tt&gt;retainAll&lt;/tt&gt; and &lt;tt&gt;clear&lt;/tt&gt; operations.  It does not
  736 &nbsp;      * support the &lt;tt&gt;add&lt;/tt&gt; or &lt;tt&gt;addAll&lt;/tt&gt; operations.
  737 &nbsp;      *
  738 &nbsp;      * @since 1.2
  739 &nbsp;      */
  740 &nbsp;     public Collection&lt;V&gt; values() {
<a name='741'>  741 &nbsp;         if (values==null)
  742 &nbsp;             values = Collections.synchronizedCollection(new ValueCollection(),
  743 &nbsp;                                                         this);
  744 &nbsp;         return values;
  745 &nbsp;     }
  746 &nbsp; 
  747 &nbsp;     private class ValueCollection extends AbstractCollection&lt;V&gt; {
  748 &nbsp;         public Iterator&lt;V&gt; iterator() {
  749 &nbsp;             return getIterator(VALUES);
  750 &nbsp;         }
<a name='751'>  751 &nbsp;         public int size() {
  752 &nbsp;             return count;
  753 &nbsp;         }
  754 &nbsp;         public boolean contains(Object o) {
  755 &nbsp;             return containsValue(o);
  756 &nbsp;         }
  757 &nbsp;         public void clear() {
  758 &nbsp;             Hashtable.this.clear();
  759 &nbsp;         }
  760 &nbsp;     }
<a name='761'>  761 &nbsp; 
  762 &nbsp;     // Comparison and hashing
  763 &nbsp; 
  764 &nbsp;     /**
  765 &nbsp;      * Compares the specified Object with this Map for equality,
  766 &nbsp;      * as per the definition in the Map interface.
  767 &nbsp;      *
  768 &nbsp;      * @param  o object to be compared for equality with this hashtable
  769 &nbsp;      * @return true if the specified Object is equal to this Map
  770 &nbsp;      * @see Map#equals(Object)
<a name='771'>  771 &nbsp;      * @since 1.2
  772 &nbsp;      */
  773 &nbsp;     public synchronized boolean equals(Object o) {
  774 &nbsp;         if (o == this)
  775 &nbsp;             return true;
  776 &nbsp; 
  777 &nbsp;         if (!(o instanceof Map))
  778 &nbsp;             return false;
  779 &nbsp;         Map&lt;K,V&gt; t = (Map&lt;K,V&gt;) o;
  780 &nbsp;         if (t.size() != size())
<a name='781'>  781 &nbsp;             return false;
  782 &nbsp; 
  783 &nbsp;         try {
  784 &nbsp;             Iterator&lt;Map.Entry&lt;K,V&gt;&gt; i = entrySet().iterator();
  785 &nbsp;             while (i.hasNext()) {
  786 &nbsp;                 Map.Entry&lt;K,V&gt; e = i.next();
  787 &nbsp;                 K key = e.getKey();
  788 &nbsp;                 V value = e.getValue();
  789 &nbsp;                 if (value == null) {
  790 &nbsp;                     if (!(t.get(key)==null &amp;&amp; t.containsKey(key)))
<a name='791'>  791 &nbsp;                         return false;
  792 &nbsp;                 } else {
  793 &nbsp;                     if (!value.equals(t.get(key)))
  794 &nbsp;                         return false;
  795 &nbsp;                 }
  796 &nbsp;             }
  797 &nbsp;         } catch (ClassCastException unused)   {
  798 &nbsp;             return false;
  799 &nbsp;         } catch (NullPointerException unused) {
  800 &nbsp;             return false;
<a name='801'>  801 &nbsp;         }
  802 &nbsp; 
  803 &nbsp;         return true;
  804 &nbsp;     }
  805 &nbsp; 
  806 &nbsp;     /**
  807 &nbsp;      * Returns the hash code value for this Map as per the definition in the
  808 &nbsp;      * Map interface.
  809 &nbsp;      *
  810 &nbsp;      * @see Map#hashCode()
<a name='811'>  811 &nbsp;      * @since 1.2
  812 &nbsp;      */
  813 &nbsp;     public synchronized int hashCode() {
  814 &nbsp;         /*
  815 &nbsp;          * This code detects the recursion caused by computing the hash code
  816 &nbsp;          * of a self-referential hash table and prevents the stack overflow
  817 &nbsp;          * that would otherwise result.  This allows certain 1.1-era
  818 &nbsp;          * applets with self-referential hash tables to work.  This code
  819 &nbsp;          * abuses the loadFactor field to do double-duty as a hashCode
  820 &nbsp;          * in progress flag, so as not to worsen the space performance.
<a name='821'>  821 &nbsp;          * A negative load factor indicates that hash code computation is
  822 &nbsp;          * in progress.
  823 &nbsp;          */
  824 &nbsp;         int h = 0;
  825 &nbsp;         if (count == 0 || loadFactor &lt; 0)
  826 &nbsp;             return h;  // Returns zero
  827 &nbsp; 
  828 &nbsp;         loadFactor = -loadFactor;  // Mark hashCode computation in progress
  829 &nbsp;         Entry[] tab = table;
  830 &nbsp;         for (int i = 0; i &lt; tab.length; i++)
<a name='831'>  831 &nbsp;             for (Entry e = tab[i]; e != null; e = e.next)
  832 &nbsp;                 h += e.key.hashCode() ^ e.value.hashCode();
  833 &nbsp;         loadFactor = -loadFactor;  // Mark hashCode computation complete
  834 &nbsp; 
  835 &nbsp;         return h;
  836 &nbsp;     }
  837 &nbsp; 
  838 &nbsp;     /**
  839 &nbsp;      * Save the state of the Hashtable to a stream (i.e., serialize it).
  840 &nbsp;      *
<a name='841'>  841 &nbsp;      * @serialData The &lt;i&gt;capacity&lt;/i&gt; of the Hashtable (the length of the
  842 &nbsp;      *             bucket array) is emitted (int), followed by the
  843 &nbsp;      *             &lt;i&gt;size&lt;/i&gt; of the Hashtable (the number of key-value
  844 &nbsp;      *             mappings), followed by the key (Object) and value (Object)
  845 &nbsp;      *             for each key-value mapping represented by the Hashtable
  846 &nbsp;      *             The key-value mappings are emitted in no particular order.
  847 &nbsp;      */
  848 &nbsp;     private void writeObject(java.io.ObjectOutputStream s)
  849 &nbsp;             throws IOException {
  850 &nbsp;         Entry&lt;Object, Object&gt; entryStack = null;
<a name='851'>  851 &nbsp; 
  852 &nbsp;         synchronized (this) {
  853 &nbsp;             // Write out the length, threshold, loadfactor
  854 &nbsp;             s.defaultWriteObject();
  855 &nbsp; 
  856 &nbsp;             // Write out length, count of elements
  857 &nbsp;             s.writeInt(table.length);
  858 &nbsp;             s.writeInt(count);
  859 &nbsp; 
  860 &nbsp;             // Stack copies of the entries in the table
<a name='861'>  861 &nbsp;             for (int index = 0; index &lt; table.length; index++) {
  862 &nbsp;                 Entry entry = table[index];
  863 &nbsp; 
  864 &nbsp;                 while (entry != null) {
  865 &nbsp;                     entryStack =
  866 &nbsp;                         new Entry&lt;&gt;(0, entry.key, entry.value, entryStack);
  867 &nbsp;                     entry = entry.next;
  868 &nbsp;                 }
  869 &nbsp;             }
  870 &nbsp;         }
<a name='871'>  871 &nbsp; 
  872 &nbsp;         // Write out the key/value objects from the stacked entries
  873 &nbsp;         while (entryStack != null) {
  874 &nbsp;             s.writeObject(entryStack.key);
  875 &nbsp;             s.writeObject(entryStack.value);
  876 &nbsp;             entryStack = entryStack.next;
  877 &nbsp;         }
  878 &nbsp;     }
  879 &nbsp; 
  880 &nbsp;     /**
<a name='881'>  881 &nbsp;      * Reconstitute the Hashtable from a stream (i.e., deserialize it).
  882 &nbsp;      */
  883 &nbsp;     private void readObject(java.io.ObjectInputStream s)
  884 &nbsp;          throws IOException, ClassNotFoundException
  885 &nbsp;     {
  886 &nbsp;         // Read in the length, threshold, and loadfactor
  887 &nbsp;         s.defaultReadObject();
  888 &nbsp; 
  889 &nbsp;         // Read the original length of the array and number of elements
  890 &nbsp;         int origlength = s.readInt();
<a name='891'>  891 &nbsp;         int elements = s.readInt();
  892 &nbsp; 
  893 &nbsp;         // Compute new size with a bit of room 5% to grow but
  894 &nbsp;         // no larger than the original size.  Make the length
  895 &nbsp;         // odd if it's large enough, this helps distribute the entries.
  896 &nbsp;         // Guard against the length ending up zero, that's not valid.
  897 &nbsp;         int length = (int)(elements * loadFactor) + (elements / 20) + 3;
  898 &nbsp;         if (length &gt; elements &amp;&amp; (length &amp; 1) == 0)
  899 &nbsp;             length--;
  900 &nbsp;         if (origlength &gt; 0 &amp;&amp; length &gt; origlength)
<a name='901'>  901 &nbsp;             length = origlength;
  902 &nbsp; 
  903 &nbsp;         Entry[] table = new Entry[length];
  904 &nbsp;         count = 0;
  905 &nbsp; 
  906 &nbsp;         // Read the number of elements and then all the key/value objects
  907 &nbsp;         for (; elements &gt; 0; elements--) {
  908 &nbsp;             K key = (K)s.readObject();
  909 &nbsp;             V value = (V)s.readObject();
  910 &nbsp;             // synch could be eliminated for performance
<a name='911'>  911 &nbsp;             reconstitutionPut(table, key, value);
  912 &nbsp;         }
  913 &nbsp;         this.table = table;
  914 &nbsp;     }
  915 &nbsp; 
  916 &nbsp;     /**
  917 &nbsp;      * The put method used by readObject. This is provided because put
  918 &nbsp;      * is overridable and should not be called in readObject since the
  919 &nbsp;      * subclass will not yet be initialized.
  920 &nbsp;      *
<a name='921'>  921 &nbsp;      * &lt;p&gt;This differs from the regular put method in several ways. No
  922 &nbsp;      * checking for rehashing is necessary since the number of elements
  923 &nbsp;      * initially in the table is known. The modCount is not incremented
  924 &nbsp;      * because we are creating a new instance. Also, no return value
  925 &nbsp;      * is needed.
  926 &nbsp;      */
  927 &nbsp;     private void reconstitutionPut(Entry[] tab, K key, V value)
  928 &nbsp;         throws StreamCorruptedException
  929 &nbsp;     {
  930 &nbsp;         if (value == null) {
<a name='931'>  931 &nbsp;             throw new java.io.StreamCorruptedException();
  932 &nbsp;         }
  933 &nbsp;         // Makes sure the key is not already in the hashtable.
  934 &nbsp;         // This should not happen in deserialized version.
  935 &nbsp;         int hash = key.hashCode();
  936 &nbsp;         int index = (hash &amp; 0x7FFFFFFF) % tab.length;
  937 &nbsp;         for (Entry&lt;K,V&gt; e = tab[index] ; e != null ; e = e.next) {
  938 &nbsp;             if ((e.hash == hash) &amp;&amp; e.key.equals(key)) {
  939 &nbsp;                 throw new java.io.StreamCorruptedException();
  940 &nbsp;             }
<a name='941'>  941 &nbsp;         }
  942 &nbsp;         // Creates the new entry.
  943 &nbsp;         Entry&lt;K,V&gt; e = tab[index];
  944 &nbsp;         tab[index] = new Entry&lt;&gt;(hash, key, value, e);
  945 &nbsp;         count++;
  946 &nbsp;     }
  947 &nbsp; 
  948 &nbsp;     /**
  949 &nbsp;      * Hashtable collision list.
  950 &nbsp;      */
<a name='951'>  951 &nbsp;     private static class Entry&lt;K,V&gt; implements Map.Entry&lt;K,V&gt; {
  952 &nbsp;         int hash;
  953 &nbsp;         K key;
  954 &nbsp;         V value;
  955 &nbsp;         Entry&lt;K,V&gt; next;
  956 &nbsp; 
  957 &nbsp;         protected Entry(int hash, K key, V value, Entry&lt;K,V&gt; next) {
  958 &nbsp;             this.hash = hash;
  959 &nbsp;             this.key = key;
  960 &nbsp;             this.value = value;
<a name='961'>  961 &nbsp;             this.next = next;
  962 &nbsp;         }
  963 &nbsp; 
  964 &nbsp;         protected Object clone() {
  965 &nbsp;             return new Entry&lt;&gt;(hash, key, value,
  966 &nbsp;                                   (next==null ? null : (Entry&lt;K,V&gt;) next.clone()));
  967 &nbsp;         }
  968 &nbsp; 
  969 &nbsp;         // Map.Entry Ops
  970 &nbsp; 
<a name='971'>  971 &nbsp;         public K getKey() {
  972 &nbsp;             return key;
  973 &nbsp;         }
  974 &nbsp; 
  975 &nbsp;         public V getValue() {
  976 &nbsp;             return value;
  977 &nbsp;         }
  978 &nbsp; 
  979 &nbsp;         public V setValue(V value) {
  980 &nbsp;             if (value == null)
<a name='981'>  981 &nbsp;                 throw new NullPointerException();
  982 &nbsp; 
  983 &nbsp;             V oldValue = this.value;
  984 &nbsp;             this.value = value;
  985 &nbsp;             return oldValue;
  986 &nbsp;         }
  987 &nbsp; 
  988 &nbsp;         public boolean equals(Object o) {
  989 &nbsp;             if (!(o instanceof Map.Entry))
  990 &nbsp;                 return false;
<a name='991'>  991 &nbsp;             Map.Entry e = (Map.Entry)o;
  992 &nbsp; 
  993 &nbsp;             return (key==null ? e.getKey()==null : key.equals(e.getKey())) &amp;&amp;
  994 &nbsp;                (value==null ? e.getValue()==null : value.equals(e.getValue()));
  995 &nbsp;         }
  996 &nbsp; 
  997 &nbsp;         public int hashCode() {
  998 &nbsp;             return hash ^ (value==null ? 0 : value.hashCode());
  999 &nbsp;         }
 1000 &nbsp; 
<a name='1001'> 1001 &nbsp;         public String toString() {
 1002 &nbsp;             return key.toString()+"="+value.toString();
 1003 &nbsp;         }
 1004 &nbsp;     }
 1005 &nbsp; 
 1006 &nbsp;     // Types of Enumerations/Iterations
 1007 &nbsp;     private static final int KEYS = 0;
 1008 &nbsp;     private static final int VALUES = 1;
 1009 &nbsp;     private static final int ENTRIES = 2;
 1010 &nbsp; 
<a name='1011'> 1011 &nbsp;     /**
 1012 &nbsp;      * A hashtable enumerator class.  This class implements both the
 1013 &nbsp;      * Enumeration and Iterator interfaces, but individual instances
 1014 &nbsp;      * can be created with the Iterator methods disabled.  This is necessary
 1015 &nbsp;      * to avoid unintentionally increasing the capabilities granted a user
 1016 &nbsp;      * by passing an Enumeration.
 1017 &nbsp;      */
 1018 &nbsp;     private class Enumerator&lt;T&gt; implements Enumeration&lt;T&gt;, Iterator&lt;T&gt; {
 1019 &nbsp;         Entry[] table = Hashtable.this.table;
 1020 &nbsp;         int index = table.length;
<a name='1021'> 1021 &nbsp;         Entry&lt;K,V&gt; entry = null;
 1022 &nbsp;         Entry&lt;K,V&gt; lastReturned = null;
 1023 &nbsp;         int type;
 1024 &nbsp; 
 1025 &nbsp;         /**
 1026 &nbsp;          * Indicates whether this Enumerator is serving as an Iterator
 1027 &nbsp;          * or an Enumeration.  (true -&gt; Iterator).
 1028 &nbsp;          */
 1029 &nbsp;         boolean iterator;
 1030 &nbsp; 
<a name='1031'> 1031 &nbsp;         /**
 1032 &nbsp;          * The modCount value that the iterator believes that the backing
 1033 &nbsp;          * Hashtable should have.  If this expectation is violated, the iterator
 1034 &nbsp;          * has detected concurrent modification.
 1035 &nbsp;          */
 1036 &nbsp;         protected int expectedModCount = modCount;
 1037 &nbsp; 
 1038 &nbsp;         Enumerator(int type, boolean iterator) {
 1039 &nbsp;             this.type = type;
 1040 &nbsp;             this.iterator = iterator;
<a name='1041'> 1041 &nbsp;         }
 1042 &nbsp; 
 1043 &nbsp;         public boolean hasMoreElements() {
 1044 &nbsp;             Entry&lt;K,V&gt; e = entry;
 1045 &nbsp;             int i = index;
 1046 &nbsp;             Entry[] t = table;
 1047 &nbsp;             /* Use locals for faster loop iteration */
 1048 &nbsp;             while (e == null &amp;&amp; i &gt; 0) {
 1049 &nbsp;                 e = t[--i];
 1050 &nbsp;             }
<a name='1051'> 1051 &nbsp;             entry = e;
 1052 &nbsp;             index = i;
 1053 &nbsp;             return e != null;
 1054 &nbsp;         }
 1055 &nbsp; 
 1056 &nbsp;         public T nextElement() {
 1057 &nbsp;             Entry&lt;K,V&gt; et = entry;
 1058 &nbsp;             int i = index;
 1059 &nbsp;             Entry[] t = table;
 1060 &nbsp;             /* Use locals for faster loop iteration */
<a name='1061'> 1061 &nbsp;             while (et == null &amp;&amp; i &gt; 0) {
 1062 &nbsp;                 et = t[--i];
 1063 &nbsp;             }
 1064 &nbsp;             entry = et;
 1065 &nbsp;             index = i;
 1066 &nbsp;             if (et != null) {
 1067 &nbsp;                 Entry&lt;K,V&gt; e = lastReturned = entry;
 1068 &nbsp;                 entry = e.next;
 1069 &nbsp;                 return type == KEYS ? (T)e.key : (type == VALUES ? (T)e.value : (T)e);
 1070 &nbsp;             }
<a name='1071'> 1071 &nbsp;             throw new NoSuchElementException("Hashtable Enumerator");
 1072 &nbsp;         }
 1073 &nbsp; 
 1074 &nbsp;         // Iterator methods
 1075 &nbsp;         public boolean hasNext() {
 1076 &nbsp;             return hasMoreElements();
 1077 &nbsp;         }
 1078 &nbsp; 
 1079 &nbsp;         public T next() {
 1080 &nbsp;             if (modCount != expectedModCount)
<a name='1081'> 1081 &nbsp;                 throw new ConcurrentModificationException();
 1082 &nbsp;             return nextElement();
 1083 &nbsp;         }
 1084 &nbsp; 
 1085 &nbsp;         public void remove() {
 1086 &nbsp;             if (!iterator)
 1087 &nbsp;                 throw new UnsupportedOperationException();
 1088 &nbsp;             if (lastReturned == null)
 1089 &nbsp;                 throw new IllegalStateException("Hashtable Enumerator");
 1090 &nbsp;             if (modCount != expectedModCount)
<a name='1091'> 1091 &nbsp;                 throw new ConcurrentModificationException();
 1092 &nbsp; 
 1093 &nbsp;             synchronized(Hashtable.this) {
 1094 &nbsp;                 Entry[] tab = Hashtable.this.table;
 1095 &nbsp;                 int index = (lastReturned.hash &amp; 0x7FFFFFFF) % tab.length;
 1096 &nbsp; 
 1097 &nbsp;                 for (Entry&lt;K,V&gt; e = tab[index], prev = null; e != null;
 1098 &nbsp;                      prev = e, e = e.next) {
 1099 &nbsp;                     if (e == lastReturned) {
 1100 &nbsp;                         modCount++;
<a name='1101'> 1101 &nbsp;                         expectedModCount++;
 1102 &nbsp;                         if (prev == null)
 1103 &nbsp;                             tab[index] = e.next;
 1104 &nbsp;                         else
 1105 &nbsp;                             prev.next = e.next;
 1106 &nbsp;                         count--;
 1107 &nbsp;                         lastReturned = null;
 1108 &nbsp;                         return;
 1109 &nbsp;                     }
 1110 &nbsp;                 }
<a name='1111'> 1111 &nbsp;                 throw new ConcurrentModificationException();
 1112 &nbsp;             }
 1113 &nbsp;         }
 1114 &nbsp;     }
 1115 &nbsp; }

</pre>
<div style="width:100%;background-color:#ddeeff;border:1px solid #ccddee;margin:0 0 0 0;padding:2px 2px 2px 2px">
<div style="float:right"><a href="http://del.icio.us/post" onclick="window.open('http://del.icio.us/post?v=4&noui&jump=close&url='+encodeURIComponent(location.href)+'&title='+encodeURIComponent(document.title), 'delicious','toolbar=no,width=700,height=400'); return false;"><img src="http://images.del.icio.us/static/img/delicious.small.gif" border=0> Save This Page</a></div>
<a href="/">Home</a> &#187; <a href="/projects/openjdk-7-java.html">openjdk-7</a> &#187; java &#187;  <a href='/docs/api/java/util/package-index.html'>util</a> &#187; 
 [<a href="/docs/api/java/util/Hashtable.html">javadoc</a> | source]
</div>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-138120-1");
pageTracker._trackPageview();
</script>
</body>
</html>






<!-- end -->