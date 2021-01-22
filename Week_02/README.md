学习笔记

串行GC:
      java -Xms512M -Xmx512M -XX:+UseSerialGC  -Xlog:gc*  GCLogAnalysis
      
[0.018s][info][gc] Using Serial

[0.018s][info][gc,heap,coops] Heap address: 0x00000000e0000000, size: 512 MB, Compressed Oops mode: 32-bit

正在执行...

[0.170s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)   //空间分配失败->触发YOUNG GC 

[0.195s][info][gc,heap      ] GC(0) DefNew: 139709K->17472K(157248K)   //  新生代回收

[0.196s][info][gc,heap      ] GC(0) Tenured: 0K->34033K(349568K)       // 34033K 晋升老年代

[0.196s][info][gc,metaspace ] GC(0) Metaspace: 3986K->3986K(1056768K)

[0.196s][info][gc           ] GC(0) Pause Young (Allocation Failure) 136M->50M(494M) 25.717ms

[0.196s][info][gc,cpu       ] GC(0) User=0.02s Sys=0.02s Real=0.03s
...
[1.042s][info][gc,start       ] GC(24) Pause Young (Allocation Failure)

[1.042s][info][gc,start       ] GC(25) Pause Full (Allocation Failure)  //空间分配失败-> 触发FULL GC

[1.043s][info][gc,phases,start] GC(25) Phase 1: Mark live objects       //标记对象

[1.044s][info][gc,phases      ] GC(25) Phase 1: Mark live objects 1.330ms

[1.045s][info][gc,phases,start] GC(25) Phase 2: Compute new object addresses   //计算新地址

[1.045s][info][gc,phases      ] GC(25) Phase 2: Compute new object addresses 0.787ms

[1.046s][info][gc,phases,start] GC(25) Phase 3: Adjust pointers               //调整指针

[1.046s][info][gc,phases      ] GC(25) Phase 3: Adjust pointers 0.697ms

[1.046s][info][gc,phases,start] GC(25) Phase 4: Move objects                   //移动对象

[1.084s][info][gc,phases      ] GC(25) Phase 4: Move objects 37.247ms

[1.084s][info][gc             ] GC(25) Pause Full (Allocation Failure) 453M->337M(494M) 41.644ms

[1.084s][info][gc,heap        ] GC(24) DefNew: 139776K->0K(157248K)

[1.084s][info][gc,heap        ] GC(24) Tenured: 324431K->345793K(349568K)

[1.085s][info][gc,metaspace   ] GC(24) Metaspace: 3986K->3986K(1056768K)

[1.085s][info][gc             ] GC(24) Pause Young (Allocation Failure) 453M->337M(494M) 43.225ms

[1.085s][info][gc,cpu         ] GC(24) User=0.05s Sys=0.00s Real=0.04s

执行结束!共生成对象次数:11096

[1.113s][info][gc,heap,exit   ] Heap

[1.113s][info][gc,heap,exit   ]  def new generation   total 157248K, used 7260K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)

[1.113s][info][gc,heap,exit   ]   eden space 139776K,   5% used [0x00000000e0000000, 0x00000000e07170c0, 0x00000000e8880000)

[1.114s][info][gc,heap,exit   ]   from space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)

[1.114s][info][gc,heap,exit   ]   to   space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)

[1.114s][info][gc,heap,exit   ]  tenured generation   total 349568K, used 345793K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)

[1.115s][info][gc,heap,exit   ]    the space 349568K,  98% used [0x00000000eaaa0000, 0x00000000ffc50700, 0x00000000ffc50800, 0x0000000100000000)

[1.115s][info][gc,heap,exit   ]  Metaspace       used 4980K, capacity 5037K, committed 5120K, reserved 1056768K

[1.115s][info][gc,heap,exit   ]   class space    used 418K, capacity 444K, committed 512K, reserved 1048576K





    
