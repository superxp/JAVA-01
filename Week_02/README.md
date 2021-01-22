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

并行GC:
        java -Xms512M -Xmx512M -XX:+UseParallelGC      -Xlog:gc*  GCLogAnalysis   
	
		[0.019s][info][gc] Using Parallel
		[0.021s][info][gc,heap,coops] Heap address: 0x00000000e0000000, size: 512 MB, Compressed Oops mode: 32-bit
		正在执行...
		[0.176s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)  //空间分配不够->触发GC
		[0.186s][info][gc,heap      ] GC(0) PSYoungGen: 131584K->21498K(153088K)
		[0.186s][info][gc,heap      ] GC(0) ParOldGen: 0K->24188K(349696K)
		[0.187s][info][gc,metaspace ] GC(0) Metaspace: 3988K->3988K(1056768K)
		[0.196s][info][gc           ] GC(0) Pause Young (Allocation Failure) 128M->44M(491M) 19.385ms
		[0.196s][info][gc,cpu       ] GC(0) User=0.00s Sys=0.00s Real=0.02s
	        ...
		[1.089s][info][gc,start       ] GC(28) Pause Full (Ergonomics)   //
		[1.089s][info][gc,phases,start] GC(28) Marking Phase  //标记阶段
		[1.091s][info][gc,phases      ] GC(28) Marking Phase 1.315ms
		[1.091s][info][gc,phases,start] GC(28) Summary Phase   
		[1.091s][info][gc,phases      ] GC(28) Summary Phase 0.174ms
		[1.091s][info][gc,phases,start] GC(28) Adjust Roots
		[1.092s][info][gc,phases      ] GC(28) Adjust Roots 0.418ms
		[1.092s][info][gc,phases,start] GC(28) Compaction Phase
		[1.125s][info][gc,phases      ] GC(28) Compaction Phase 33.327ms
		[1.126s][info][gc,phases,start] GC(28) Post Compact
		[1.127s][info][gc,phases      ] GC(28) Post Compact 1.496ms
		[1.127s][info][gc,heap        ] GC(28) PSYoungGen: 19576K->0K(116736K)
		[1.128s][info][gc,heap        ] GC(28) ParOldGen: 320309K->303633K(349696K)
		[1.128s][info][gc,metaspace   ] GC(28) Metaspace: 3988K->3988K(1056768K)
		[1.128s][info][gc             ] GC(28) Pause Full (Ergonomics) 331M->296M(455M) 38.965ms
		[1.128s][info][gc,cpu         ] GC(28) User=0.13s Sys=0.00s Real=0.04s
		执行结束!共生成对象次数:8415
		[1.159s][info][gc,heap,exit   ] Heap
		[1.159s][info][gc,heap,exit   ]  PSYoungGen      total 116736K, used 2729K [0x00000000f5580000, 0x0000000100000000, 0x0000000100000000)
		[1.159s][info][gc,heap,exit   ]   eden space 58880K, 4% used [0x00000000f5580000,0x00000000f582a4c8,0x00000000f8f00000)
		[1.159s][info][gc,heap,exit   ]   from space 57856K, 0% used [0x00000000f8f00000,0x00000000f8f00000,0x00000000fc780000)
		[1.160s][info][gc,heap,exit   ]   to   space 57856K, 0% used [0x00000000fc780000,0x00000000fc780000,0x0000000100000000)
		[1.160s][info][gc,heap,exit   ]  ParOldGen       total 349696K, used 303633K [0x00000000e0000000, 0x00000000f5580000, 0x00000000f5580000)
		[1.160s][info][gc,heap,exit   ]   object space 349696K, 86% used [0x00000000e0000000,0x00000000f28845b0,0x00000000f5580000)
		[1.160s][info][gc,heap,exit   ]  Metaspace       used 4981K, capacity 5037K, committed 5120K, reserved 1056768K
		[1.160s][info][gc,heap,exit   ]   class space    used 418K, capacity 444K, committed 512K, reserved 1048576K

并发GC:
      java -Xms512M -Xmx512M -XX:+UseConcMarkSweepGC         -Xlog:gc*  GCLogAnalysis
      
		[0.025s][info][gc] Using Concurrent Mark Sweep
		[0.025s][info][gc,heap,coops] Heap address: 0x00000000e0000000, size: 512 MB, Compressed Oops mode: 32-bit
		正在执行...
		[0.183s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
		[0.184s][info][gc,task      ] GC(0) Using 4 workers of 4 for evacuation
		[0.194s][info][gc,heap      ] GC(0) ParNew: 139776K->17470K(157248K)
		[0.194s][info][gc,heap      ] GC(0) CMS: 0K->34395K(349568K)
		[0.195s][info][gc,metaspace ] GC(0) Metaspace: 3990K->3990K(1056768K)
		[0.195s][info][gc           ] GC(0) Pause Young (Allocation Failure) 136M->50M(494M) 11.274ms
		[0.195s][info][gc,cpu       ] GC(0) User=0.02s Sys=0.05s Real=0.01s
		...
		[0.293s][info][gc,start     ] GC(3) Pause Young (Allocation Failure)
		[0.294s][info][gc,task      ] GC(3) Using 4 workers of 4 for evacuation
		[0.322s][info][gc,heap      ] GC(3) ParNew: 157248K->17472K(157248K)
		[0.322s][info][gc,heap      ] GC(3) CMS: 130093K->181149K(349568K)
		[0.323s][info][gc,metaspace ] GC(3) Metaspace: 3990K->3990K(1056768K)
		[0.323s][info][gc           ] GC(3) Pause Young (Allocation Failure) 280M->193M(494M) 29.191ms
		[0.323s][info][gc,cpu       ] GC(3) User=0.02s Sys=0.05s Real=0.03s
		[0.324s][info][gc,start     ] GC(4) Pause Initial Mark   // 暂停对外服务 初始标记
		[0.324s][info][gc           ] GC(4) Pause Initial Mark 197M->197M(494M) 0.386ms
		[0.324s][info][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.00s
		[0.324s][info][gc           ] GC(4) Concurrent Mark     //并发标记
		[0.328s][info][gc           ] GC(4) Concurrent Mark 3.954ms
		[0.328s][info][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.00s
		[0.329s][info][gc           ] GC(4) Concurrent Preclean  //并发预清理
		[0.329s][info][gc           ] GC(4) Concurrent Preclean 0.488ms
		[0.329s][info][gc,cpu       ] GC(4) User=0.02s Sys=0.00s Real=0.00s
		[0.329s][info][gc           ] GC(4) Concurrent Abortable Preclean 
	        ....
		[0.472s][info][gc           ] GC(4) Concurrent Abortable Preclean 142.292ms
		[0.472s][info][gc,cpu       ] GC(4) User=0.30s Sys=0.05s Real=0.14s
		[0.472s][info][gc,phases,start] GC(9) Phase 1: Mark live objects
		[0.473s][info][gc,phases      ] GC(9) Phase 1: Mark live objects 1.244ms
		[0.474s][info][gc,phases,start] GC(9) Phase 2: Compute new object addresses
		[0.475s][info][gc,phases      ] GC(9) Phase 2: Compute new object addresses 0.977ms
		[0.475s][info][gc,phases,start] GC(9) Phase 3: Adjust pointers
		[0.476s][info][gc,phases      ] GC(9) Phase 3: Adjust pointers 0.897ms
		[0.476s][info][gc,phases,start] GC(9) Phase 4: Move objects
		[0.511s][info][gc,phases      ] GC(9) Phase 4: Move objects 34.840ms
		[0.512s][info][gc             ] GC(9) Pause Full (Allocation Failure) 474M->238M(494M) 40.357ms
                ...
		[0.529s][info][gc,start       ] GC(10) Pause Young (Allocation Failure)
		[0.530s][info][gc,task        ] GC(10) Using 4 workers of 4 for evacuation
		[0.538s][info][gc,heap        ] GC(10) ParNew: 139766K->17472K(157248K)
		[0.539s][info][gc,heap        ] GC(10) CMS: 244429K->275938K(349568K)
		[0.540s][info][gc,metaspace   ] GC(10) Metaspace: 3990K->3990K(1056768K)
		[0.540s][info][gc             ] GC(10) Pause Young (Allocation Failure) 375M->286M(494M) 10.913ms
		[0.541s][info][gc,cpu         ] GC(10) User=0.06s Sys=0.00s Real=0.01s
		[0.541s][info][gc,start       ] GC(11) Pause Initial Mark
		[0.541s][info][gc             ] GC(11) Pause Initial Mark 289M->289M(494M) 0.214ms
		[0.541s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.00s
		[0.541s][info][gc             ] GC(11) Concurrent Mark
		[0.544s][info][gc             ] GC(11) Concurrent Mark 2.499ms
		[0.545s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.00s
		[0.545s][info][gc             ] GC(11) Concurrent Preclean
		[0.546s][info][gc             ] GC(11) Concurrent Preclean 0.719ms
		[0.546s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.00s
		[0.546s][info][gc             ] GC(11) Concurrent Abortable Preclean
		...
		[0.573s][info][gc             ] GC(11) Concurrent Abortable Preclean 27.290ms
		[0.574s][info][gc,cpu         ] GC(11) User=0.08s Sys=0.00s Real=0.03s
		[0.574s][info][gc,start       ] GC(11) Pause Remark
		[0.575s][info][gc             ] GC(11) Pause Remark 354M->354M(494M) 0.793ms
		[0.575s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.00s
		[0.575s][info][gc             ] GC(11) Concurrent Sweep
		[0.576s][info][gc             ] GC(11) Concurrent Sweep 0.960ms
		[0.577s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.00s
		[0.577s][info][gc             ] GC(11) Concurrent Reset
		[0.578s][info][gc             ] GC(11) Concurrent Reset 0.624ms
		[0.578s][info][gc,cpu         ] GC(11) User=0.00s Sys=0.00s Real=0.00s
		[0.578s][info][gc,heap        ] GC(11) Old: 275938K->295453K(349568K)
		...
		[0.688s][info][gc,start       ] GC(17) Pause Young (Allocation Failure)
		[0.688s][info][gc,task        ] GC(17) Using 4 workers of 4 for evacuation
		[0.688s][info][gc,start       ] GC(18) Pause Full (Allocation Failure)
		[0.689s][info][gc,phases,start] GC(18) Phase 1: Mark live objects
		[0.691s][info][gc,phases      ] GC(18) Phase 1: Mark live objects 1.525ms
		[0.691s][info][gc,phases,start] GC(18) Phase 2: Compute new object addresses
		[0.692s][info][gc,phases      ] GC(18) Phase 2: Compute new object addresses 1.216ms
		[0.693s][info][gc,phases,start] GC(18) Phase 3: Adjust pointers
		[0.694s][info][gc,phases      ] GC(18) Phase 3: Adjust pointers 1.133ms
		[0.694s][info][gc,phases,start] GC(18) Phase 4: Move objects
		[0.735s][info][gc,phases      ] GC(18) Phase 4: Move objects 41.075ms
		[0.737s][info][gc             ] GC(18) Pause Full (Allocation Failure) 475M->295M(494M) 48.106ms
		[0.737s][info][gc,heap        ] GC(17) ParNew: 157248K->0K(157248K)
		[0.737s][info][gc,heap        ] GC(17) CMS: 329552K->302560K(349568K)
		[0.737s][info][gc,metaspace   ] GC(17) Metaspace: 3990K->3990K(1056768K)
		[0.738s][info][gc             ] GC(17) Pause Young (Allocation Failure) 475M->295M(494M) 50.051ms
		[0.738s][info][gc,cpu         ] GC(17) User=0.06s Sys=0.00s Real=0.05s
		[0.738s][info][gc,start       ] GC(19) Pause Initial Mark
		[0.738s][info][gc             ] GC(19) Pause Initial Mark 295M->295M(494M) 0.246ms
		[0.739s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.00s
		[0.739s][info][gc             ] GC(19) Concurrent Mark
		[0.742s][info][gc             ] GC(19) Concurrent Mark 2.745ms
		[0.742s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.00s
		[0.742s][info][gc             ] GC(19) Concurrent Preclean
		[0.743s][info][gc             ] GC(19) Concurrent Preclean 0.625ms
		[0.743s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.00s
		[0.743s][info][gc             ] GC(19) Concurrent Abortable Preclean
		[0.755s][info][gc,start       ] GC(20) Pause Young (Allocation Failure)
		[0.756s][info][gc,task        ] GC(20) Using 4 workers of 4 for evacuation
		[0.770s][info][gc,heap        ] GC(20) ParNew: 139776K->17472K(157248K)
		[0.770s][info][gc,heap        ] GC(20) CMS: 302560K->346212K(349568K)
		[0.770s][info][gc,metaspace   ] GC(20) Metaspace: 3990K->3990K(1056768K)
		[0.770s][info][gc             ] GC(20) Pause Young (Allocation Failure) 431M->355M(494M) 15.085ms
		[0.771s][info][gc,cpu         ] GC(20) User=0.05s Sys=0.00s Real=0.01s
		[0.771s][info][gc             ] GC(19) Concurrent Abortable Preclean 27.429ms
		[0.771s][info][gc,cpu         ] GC(19) User=0.06s Sys=0.00s Real=0.03s
		[0.771s][info][gc,start       ] GC(19) Pause Remark
		[0.772s][info][gc             ] GC(19) Pause Remark 358M->358M(494M) 1.047ms
		[0.772s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.00s
		[0.773s][info][gc             ] GC(19) Concurrent Sweep
		[0.773s][info][gc             ] GC(19) Concurrent Sweep 0.594ms
		[0.773s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.00s
		[0.774s][info][gc             ] GC(19) Concurrent Reset
		[0.774s][info][gc             ] GC(19) Concurrent Reset 0.778ms
		[0.777s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.00s
		[0.778s][info][gc,heap        ] GC(19) Old: 302560K->344647K(349568K)
		[0.791s][info][gc,start       ] GC(21) Pause Young (Allocation Failure)
		[0.792s][info][gc,task        ] GC(21) Using 4 workers of 4 for evacuation
		[0.792s][info][gc,start       ] GC(22) Pause Full (Allocation Failure)
		[0.793s][info][gc,phases,start] GC(22) Phase 1: Mark live objects
		[0.794s][info][gc,phases      ] GC(22) Phase 1: Mark live objects 1.213ms
		[0.794s][info][gc,phases,start] GC(22) Phase 2: Compute new object addresses
		[0.795s][info][gc,phases      ] GC(22) Phase 2: Compute new object addresses 0.880ms
		[0.795s][info][gc,phases,start] GC(22) Phase 3: Adjust pointers
		[0.796s][info][gc,phases      ] GC(22) Phase 3: Adjust pointers 0.829ms
		[0.797s][info][gc,phases,start] GC(22) Phase 4: Move objects
		[0.844s][info][gc,phases      ] GC(22) Phase 4: Move objects 47.117ms
		[0.845s][info][gc             ] GC(22) Pause Full (Allocation Failure) 490M->315M(494M) 53.057ms
		[0.846s][info][gc,heap        ] GC(21) ParNew: 157248K->0K(157248K)
		[0.846s][info][gc,heap        ] GC(21) CMS: 344647K->323069K(349568K)
		[0.847s][info][gc,metaspace   ] GC(21) Metaspace: 3990K->3990K(1056768K)
		[0.847s][info][gc             ] GC(21) Pause Young (Allocation Failure) 490M->315M(494M) 55.785ms
		[0.847s][info][gc,cpu         ] GC(21) User=0.03s Sys=0.00s Real=0.06s
		[0.848s][info][gc,start       ] GC(23) Pause Initial Mark
		[0.848s][info][gc             ] GC(23) Pause Initial Mark 315M->315M(494M) 0.255ms
		[0.848s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.848s][info][gc             ] GC(23) Concurrent Mark
		[0.851s][info][gc             ] GC(23) Concurrent Mark 2.600ms
		[0.851s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.851s][info][gc             ] GC(23) Concurrent Preclean
		[0.852s][info][gc             ] GC(23) Concurrent Preclean 0.582ms
		[0.852s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.852s][info][gc             ] GC(23) Concurrent Abortable Preclean
		[0.853s][info][gc             ] GC(23) Concurrent Abortable Preclean 0.175ms
		[0.855s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.856s][info][gc,start       ] GC(23) Pause Remark
		[0.857s][info][gc             ] GC(23) Pause Remark 370M->370M(494M) 1.035ms
		[0.857s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.858s][info][gc             ] GC(23) Concurrent Sweep
		[0.858s][info][gc             ] GC(23) Concurrent Sweep 0.619ms
		[0.858s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.859s][info][gc             ] GC(23) Concurrent Reset
		[0.859s][info][gc             ] GC(23) Concurrent Reset 0.560ms
		[0.859s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.860s][info][gc,heap        ] GC(23) Old: 323069K->322414K(349568K)
		[0.868s][info][gc,start       ] GC(24) Pause Young (Allocation Failure)
		[0.869s][info][gc,task        ] GC(24) Using 4 workers of 4 for evacuation
		[0.869s][info][gc,start       ] GC(25) Pause Full (Allocation Failure)
		[0.870s][info][gc,phases,start] GC(25) Phase 1: Mark live objects
		[0.872s][info][gc,phases      ] GC(25) Phase 1: Mark live objects 1.513ms
		[0.872s][info][gc,phases,start] GC(25) Phase 2: Compute new object addresses
		[0.873s][info][gc,phases      ] GC(25) Phase 2: Compute new object addresses 0.934ms
		[0.873s][info][gc,phases,start] GC(25) Phase 3: Adjust pointers
		[0.874s][info][gc,phases      ] GC(25) Phase 3: Adjust pointers 0.730ms
		[0.874s][info][gc,phases,start] GC(25) Phase 4: Move objects
		[0.920s][info][gc,phases      ] GC(25) Phase 4: Move objects 45.545ms
		[0.921s][info][gc             ] GC(25) Pause Full (Allocation Failure) 451M->322M(494M) 51.317ms
		[0.921s][info][gc,heap        ] GC(24) ParNew: 139776K->0K(157248K)
		[0.922s][info][gc,heap        ] GC(24) CMS: 322414K->330422K(349568K)
		[0.922s][info][gc,metaspace   ] GC(24) Metaspace: 3990K->3990K(1056768K)
		[0.922s][info][gc             ] GC(24) Pause Young (Allocation Failure) 451M->322M(494M) 54.074ms
		[0.923s][info][gc,cpu         ] GC(24) User=0.05s Sys=0.00s Real=0.05s
		[0.923s][info][gc,start       ] GC(26) Pause Initial Mark
		[0.923s][info][gc             ] GC(26) Pause Initial Mark 323M->323M(494M) 0.269ms
		[0.923s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.924s][info][gc             ] GC(26) Concurrent Mark
		[0.926s][info][gc             ] GC(26) Concurrent Mark 2.563ms
		[0.927s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.927s][info][gc             ] GC(26) Concurrent Preclean
		[0.928s][info][gc             ] GC(26) Concurrent Preclean 0.776ms
		[0.928s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.928s][info][gc             ] GC(26) Concurrent Abortable Preclean
		[0.929s][info][gc             ] GC(26) Concurrent Abortable Preclean 0.185ms
		[0.932s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.932s][info][gc,start       ] GC(26) Pause Remark
		[0.933s][info][gc             ] GC(26) Pause Remark 379M->379M(494M) 0.833ms
		[0.933s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.933s][info][gc             ] GC(26) Concurrent Sweep
		[0.934s][info][gc             ] GC(26) Concurrent Sweep 1.009ms
		[0.934s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.935s][info][gc             ] GC(26) Concurrent Reset
		[0.935s][info][gc             ] GC(26) Concurrent Reset 0.490ms
		[0.936s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[0.936s][info][gc,heap        ] GC(26) Old: 330422K->329886K(349568K)
		[0.944s][info][gc,start       ] GC(27) Pause Young (Allocation Failure)
		[0.945s][info][gc,task        ] GC(27) Using 4 workers of 4 for evacuation
		[0.945s][info][gc,start       ] GC(28) Pause Full (Allocation Failure)
		[0.945s][info][gc,phases,start] GC(28) Phase 1: Mark live objects
		[0.947s][info][gc,phases      ] GC(28) Phase 1: Mark live objects 1.242ms
		[0.947s][info][gc,phases,start] GC(28) Phase 2: Compute new object addresses
		[0.948s][info][gc,phases      ] GC(28) Phase 2: Compute new object addresses 0.860ms
		[0.948s][info][gc,phases,start] GC(28) Phase 3: Adjust pointers
		[0.949s][info][gc,phases      ] GC(28) Phase 3: Adjust pointers 0.990ms
		[0.949s][info][gc,phases,start] GC(28) Phase 4: Move objects
		[0.995s][info][gc,phases      ] GC(28) Phase 4: Move objects 46.117ms
		[0.996s][info][gc             ] GC(28) Pause Full (Allocation Failure) 458M->325M(494M) 51.234ms
		[0.997s][info][gc,heap        ] GC(27) ParNew: 139776K->0K(157248K)
		[0.997s][info][gc,heap        ] GC(27) CMS: 329886K->333452K(349568K)
		[0.997s][info][gc,metaspace   ] GC(27) Metaspace: 3990K->3990K(1056768K)
		[0.998s][info][gc             ] GC(27) Pause Young (Allocation Failure) 458M->325M(494M) 53.895ms
		[0.998s][info][gc,cpu         ] GC(27) User=0.05s Sys=0.00s Real=0.05s
		[0.999s][info][gc,start       ] GC(29) Pause Initial Mark
		[0.999s][info][gc             ] GC(29) Pause Initial Mark 328M->328M(494M) 0.255ms
		[0.999s][info][gc,cpu         ] GC(29) User=0.00s Sys=0.00s Real=0.00s
		[0.999s][info][gc             ] GC(29) Concurrent Mark
		[1.002s][info][gc             ] GC(29) Concurrent Mark 2.689ms
		[1.002s][info][gc,cpu         ] GC(29) User=0.03s Sys=0.00s Real=0.00s
		[1.003s][info][gc             ] GC(29) Concurrent Preclean
		[1.003s][info][gc             ] GC(29) Concurrent Preclean 0.579ms
		[1.003s][info][gc,cpu         ] GC(29) User=0.00s Sys=0.00s Real=0.00s
		[1.004s][info][gc             ] GC(29) Concurrent Abortable Preclean
		[1.004s][info][gc             ] GC(29) Concurrent Abortable Preclean 0.376ms
		[1.004s][info][gc,cpu         ] GC(29) User=0.00s Sys=0.00s Real=0.00s
		[1.008s][info][gc,start       ] GC(29) Pause Remark
		[1.009s][info][gc             ] GC(29) Pause Remark 379M->379M(494M) 1.151ms
		[1.009s][info][gc,cpu         ] GC(29) User=0.00s Sys=0.00s Real=0.00s
		[1.009s][info][gc             ] GC(29) Concurrent Sweep
		[1.010s][info][gc             ] GC(29) Concurrent Sweep 0.956ms
		[1.011s][info][gc,cpu         ] GC(29) User=0.00s Sys=0.00s Real=0.00s
		[1.011s][info][gc             ] GC(29) Concurrent Reset
		[1.012s][info][gc             ] GC(29) Concurrent Reset 0.514ms
		[1.012s][info][gc,cpu         ] GC(29) User=0.00s Sys=0.00s Real=0.00s
		[1.012s][info][gc,heap        ] GC(29) Old: 333452K->331619K(349568K)
		[1.022s][info][gc,start       ] GC(30) Pause Young (Allocation Failure)
		[1.022s][info][gc,task        ] GC(30) Using 4 workers of 4 for evacuation
		[1.022s][info][gc,start       ] GC(31) Pause Full (Allocation Failure)
		[1.023s][info][gc,phases,start] GC(31) Phase 1: Mark live objects
		[1.024s][info][gc,phases      ] GC(31) Phase 1: Mark live objects 1.405ms
		[1.024s][info][gc,phases,start] GC(31) Phase 2: Compute new object addresses
		[1.025s][info][gc,phases      ] GC(31) Phase 2: Compute new object addresses 1.053ms
		[1.026s][info][gc,phases,start] GC(31) Phase 3: Adjust pointers
		[1.027s][info][gc,phases      ] GC(31) Phase 3: Adjust pointers 0.781ms
		[1.028s][info][gc,phases,start] GC(31) Phase 4: Move objects
		[1.077s][info][gc,phases      ] GC(31) Phase 4: Move objects 48.742ms
		[1.078s][info][gc             ] GC(31) Pause Full (Allocation Failure) 460M->327M(494M) 55.138ms
		[1.078s][info][gc,heap        ] GC(30) ParNew: 139776K->0K(157248K)
		[1.078s][info][gc,heap        ] GC(30) CMS: 331619K->335376K(349568K)
		[1.079s][info][gc,metaspace   ] GC(30) Metaspace: 3990K->3990K(1056768K)
		[1.079s][info][gc             ] GC(30) Pause Young (Allocation Failure) 460M->327M(494M) 57.295ms
		[1.079s][info][gc,cpu         ] GC(30) User=0.05s Sys=0.00s Real=0.06s
		[1.080s][info][gc,start       ] GC(32) Pause Initial Mark
		[1.080s][info][gc             ] GC(32) Pause Initial Mark 328M->328M(494M) 0.257ms
		[1.080s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.080s][info][gc             ] GC(32) Concurrent Mark
		[1.083s][info][gc             ] GC(32) Concurrent Mark 2.764ms
		[1.083s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.084s][info][gc             ] GC(32) Concurrent Preclean
		[1.084s][info][gc             ] GC(32) Concurrent Preclean 0.752ms
		[1.085s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.085s][info][gc             ] GC(32) Concurrent Abortable Preclean
		[1.085s][info][gc             ] GC(32) Concurrent Abortable Preclean 0.189ms
		[1.088s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.088s][info][gc,start       ] GC(32) Pause Remark
		[1.089s][info][gc             ] GC(32) Pause Remark 384M->384M(494M) 0.842ms
		[1.089s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.090s][info][gc             ] GC(32) Concurrent Sweep
		[1.091s][info][gc             ] GC(32) Concurrent Sweep 1.021ms
		[1.091s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.091s][info][gc             ] GC(32) Concurrent Reset
		[1.092s][info][gc             ] GC(32) Concurrent Reset 0.417ms
		[1.092s][info][gc,cpu         ] GC(32) User=0.00s Sys=0.00s Real=0.00s
		[1.092s][info][gc,heap        ] GC(32) Old: 335376K->333772K(349568K)
		[1.100s][info][gc,start       ] GC(33) Pause Young (Allocation Failure)
		[1.109s][info][gc,task        ] GC(33) Using 4 workers of 4 for evacuation
		[1.109s][info][gc,start       ] GC(34) Pause Full (Allocation Failure)
		[1.109s][info][gc,phases,start] GC(34) Phase 1: Mark live objects
		[1.111s][info][gc,phases      ] GC(34) Phase 1: Mark live objects 1.252ms
		[1.111s][info][gc,phases,start] GC(34) Phase 2: Compute new object addresses
		[1.112s][info][gc,phases      ] GC(34) Phase 2: Compute new object addresses 1.037ms
		[1.112s][info][gc,phases,start] GC(34) Phase 3: Adjust pointers
		[1.113s][info][gc,phases      ] GC(34) Phase 3: Adjust pointers 0.758ms
		[1.113s][info][gc,phases,start] GC(34) Phase 4: Move objects
		[1.159s][info][gc,phases      ] GC(34) Phase 4: Move objects 46.357ms
		[1.161s][info][gc             ] GC(34) Pause Full (Allocation Failure) 462M->323M(494M) 51.443ms
		[1.161s][info][gc,heap        ] GC(33) ParNew: 139776K->0K(157248K)
		[1.161s][info][gc,heap        ] GC(33) CMS: 333772K->330895K(349568K)
		[1.161s][info][gc,metaspace   ] GC(33) Metaspace: 3990K->3990K(1056768K)
		[1.162s][info][gc             ] GC(33) Pause Young (Allocation Failure) 462M->323M(494M) 61.811ms
		[1.162s][info][gc,cpu         ] GC(33) User=0.06s Sys=0.00s Real=0.06s
		[1.163s][info][gc,start       ] GC(35) Pause Initial Mark
		[1.163s][info][gc             ] GC(35) Pause Initial Mark 326M->326M(494M) 0.371ms
		[1.163s][info][gc,cpu         ] GC(35) User=0.00s Sys=0.00s Real=0.00s
		[1.163s][info][gc             ] GC(35) Concurrent Mark
		[1.166s][info][gc             ] GC(35) Concurrent Mark 2.685ms
		[1.167s][info][gc,cpu         ] GC(35) User=0.00s Sys=0.00s Real=0.00s
		[1.167s][info][gc             ] GC(35) Concurrent Preclean
		[1.168s][info][gc             ] GC(35) Concurrent Preclean 0.571ms
		[1.168s][info][gc,cpu         ] GC(35) User=0.00s Sys=0.00s Real=0.00s
		[1.170s][info][gc             ] GC(35) Concurrent Abortable Preclean
		执行结束!共生成对象次数:12380
		[1.189s][info][gc             ] GC(35) Concurrent Abortable Preclean 19.116ms
		[1.189s][info][gc,cpu         ] GC(35) User=0.05s Sys=0.00s Real=0.02s
		[1.190s][info][gc,start       ] GC(35) Pause Remark
		[1.191s][info][gc             ] GC(35) Pause Remark 330M->330M(494M) 1.412ms
		[1.192s][info][gc,cpu         ] GC(35) User=0.00s Sys=0.00s Real=0.00s
		[1.192s][info][gc             ] GC(35) Concurrent Sweep
		[1.193s][info][gc             ] GC(35) Concurrent Sweep 0.794ms
		[1.193s][info][gc,cpu         ] GC(35) User=0.00s Sys=0.00s Real=0.00s
		[1.193s][info][gc             ] GC(35) Concurrent Reset
		[1.194s][info][gc             ] GC(35) Concurrent Reset 0.556ms
		[1.194s][info][gc,cpu         ] GC(35) User=0.00s Sys=0.00s Real=0.00s
		[1.194s][info][gc,heap        ] GC(35) Old: 330895K->330895K(349568K)
		[1.195s][info][gc,heap,exit   ] Heap
		[1.195s][info][gc,heap,exit   ]  par new generation   total 157248K, used 7421K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
		[1.195s][info][gc,heap,exit   ]   eden space 139776K,   5% used [0x00000000e0000000, 0x00000000e073f608, 0x00000000e8880000)
		[1.195s][info][gc,heap,exit   ]   from space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
		[1.195s][info][gc,heap,exit   ]   to   space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
		[1.196s][info][gc,heap,exit   ]  concurrent mark-sweep generation total 349568K, used 330895K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
		[1.196s][info][gc,heap,exit   ]  Metaspace       used 4975K, capacity 5037K, committed 5120K, reserved 1056768K
		[1.196s][info][gc,heap,exit   ]   class space    used 418K, capacity 444K, committed 512K, reserved 1048576K
G1 GC:
   java -Xms1G -Xmx1G -XX:+UseG1GC        -Xlog:gc*  GCLogAnalysis
   
		[0.018s][info][gc,heap] Heap region size: 1M
		[0.040s][info][gc     ] Using G1
		[0.047s][info][gc,heap,coops] Heap address: 0x00000000c0000000, size: 1024 MB, Compressed Oops mode: 32-bit
		正在执行...
		[0.165s][info][gc,start     ] GC(0) Pause Young (Normal) (G1 Evacuation Pause)  //触发YOUNG GC 
		[0.166s][info][gc,task      ] GC(0) Using 4 workers of 4 for evacuation
		[0.171s][info][gc,phases    ] GC(0)   Pre Evacuate Collection Set: 0.0ms
		[0.171s][info][gc,phases    ] GC(0)   Evacuate Collection Set: 3.8ms
		[0.172s][info][gc,phases    ] GC(0)   Post Evacuate Collection Set: 0.2ms
		[0.172s][info][gc,phases    ] GC(0)   Other: 1.2ms
		[0.172s][info][gc,heap      ] GC(0) Eden regions: 51->0(44)
		[0.172s][info][gc,heap      ] GC(0) Survivor regions: 0->7(7)
		[0.173s][info][gc,heap      ] GC(0) Old regions: 0->17
		[0.173s][info][gc,heap      ] GC(0) Humongous regions: 15->7
		[0.173s][info][gc,metaspace ] GC(0) Metaspace: 3986K->3986K(1056768K)
		[0.173s][info][gc           ] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 65M->30M(1024M) 7.877ms
		[0.174s][info][gc,cpu       ] GC(0) User=0.02s Sys=0.00s Real=0.01s
		...
		[0.995s][info][gc,start      ] GC(34) Pause Young (Mixed) (G1 Evacuation Pause)
		[1.004s][info][gc,task       ] GC(34) Using 4 workers of 4 for evacuation
		[1.015s][info][gc,phases     ] GC(34)   Pre Evacuate Collection Set: 0.3ms
		[1.016s][info][gc,phases     ] GC(34)   Evacuate Collection Set: 8.8ms
		[1.017s][info][gc,phases     ] GC(34)   Post Evacuate Collection Set: 0.5ms
		[1.018s][info][gc,phases     ] GC(34)   Other: 10.1ms
		[1.018s][info][gc,heap       ] GC(34) Eden regions: 44->0(177)
		[1.019s][info][gc,heap       ] GC(34) Survivor regions: 7->7(7)
		[1.020s][info][gc,heap       ] GC(34) Old regions: 361->337
		[1.020s][info][gc,heap       ] GC(34) Humongous regions: 201->181
		[1.021s][info][gc,metaspace  ] GC(34) Metaspace: 3987K->3987K(1056768K)
		[1.022s][info][gc            ] GC(34) Pause Young (Mixed) (G1 Evacuation Pause) 612M->524M(1024M) 26.846ms
		[1.023s][info][gc,cpu        ] GC(34) User=0.00s Sys=0.00s Real=0.03s
		[1.100s][info][gc,start      ] GC(35) Pause Young (Normal) (G1 Evacuation Pause)
		[1.100s][info][gc,task       ] GC(35) Using 4 workers of 4 for evacuation
		[1.108s][info][gc,phases     ] GC(35)   Pre Evacuate Collection Set: 0.1ms
		[1.110s][info][gc,phases     ] GC(35)   Evacuate Collection Set: 6.9ms
		[1.110s][info][gc,phases     ] GC(35)   Post Evacuate Collection Set: 0.3ms
		[1.110s][info][gc,phases     ] GC(35)   Other: 1.1ms
		[1.110s][info][gc,heap       ] GC(35) Eden regions: 177->0(148)
		[1.111s][info][gc,heap       ] GC(35) Survivor regions: 7->23(23)
		[1.111s][info][gc,heap       ] GC(35) Old regions: 337->395
		[1.111s][info][gc,heap       ] GC(35) Humongous regions: 292->180
		[1.111s][info][gc,metaspace  ] GC(35) Metaspace: 3987K->3987K(1056768K)
		[1.111s][info][gc            ] GC(35) Pause Young (Normal) (G1 Evacuation Pause) 812M->597M(1024M) 11.455ms
		[1.111s][info][gc,cpu        ] GC(35) User=0.02s Sys=0.00s Real=0.01s
		[1.112s][info][gc,start      ] GC(36) Pause Young (Concurrent Start) (G1 Humongous Allocation)  //巨型对象分配
		[1.113s][info][gc,task       ] GC(36) Using 4 workers of 4 for evacuation
		[1.115s][info][gc,phases     ] GC(36)   Pre Evacuate Collection Set: 0.1ms
		[1.116s][info][gc,phases     ] GC(36)   Evacuate Collection Set: 2.1ms
		[1.116s][info][gc,phases     ] GC(36)   Post Evacuate Collection Set: 0.2ms
		[1.116s][info][gc,phases     ] GC(36)   Other: 0.5ms
		[1.116s][info][gc,heap       ] GC(36) Eden regions: 7->0(136)
		[1.116s][info][gc,heap       ] GC(36) Survivor regions: 23->4(22)
		[1.117s][info][gc,heap       ] GC(36) Old regions: 395->418
		[1.117s][info][gc,heap       ] GC(36) Humongous regions: 180->173
		[1.117s][info][gc,metaspace  ] GC(36) Metaspace: 3987K->3987K(1056768K)
		[1.117s][info][gc            ] GC(36) Pause Young (Concurrent Start) (G1 Humongous Allocation) 603M->593M(1024M) 4.865ms
		[1.119s][info][gc,cpu        ] GC(36) User=0.00s Sys=0.00s Real=0.01s
		[1.120s][info][gc            ] GC(37) Concurrent Cycle
		[1.120s][info][gc,marking    ] GC(37) Concurrent Clear Claimed Marks
		[1.120s][info][gc,marking    ] GC(37) Concurrent Clear Claimed Marks 0.151ms
		[1.120s][info][gc,marking    ] GC(37) Concurrent Scan Root Regions
		[1.120s][info][gc,marking    ] GC(37) Concurrent Scan Root Regions 0.173ms
		[1.120s][info][gc,marking    ] GC(37) Concurrent Mark (1.120s)
		[1.121s][info][gc,marking    ] GC(37) Concurrent Mark From Roots
		[1.121s][info][gc,task       ] GC(37) Using 1 workers of 1 for marking
		[1.124s][info][gc,marking    ] GC(37) Concurrent Mark From Roots 3.518ms
		[1.124s][info][gc,marking    ] GC(37) Concurrent Preclean
		[1.125s][info][gc,marking    ] GC(37) Concurrent Preclean 0.436ms
		[1.125s][info][gc,marking    ] GC(37) Concurrent Mark (1.120s, 1.125s) 4.734ms
		[1.125s][info][gc,start      ] GC(37) Pause Remark
		[1.126s][info][gc,stringtable] GC(37) Cleaned string and symbol table, strings: 2198 processed, 0 removed, symbols: 19732 processed, 0 removed
		[1.126s][info][gc            ] GC(37) Pause Remark 595M->587M(1024M) 0.670ms
		[1.126s][info][gc,cpu        ] GC(37) User=0.00s Sys=0.00s Real=0.00s
		[1.126s][info][gc,marking    ] GC(37) Concurrent Rebuild Remembered Sets
		[1.130s][info][gc,marking    ] GC(37) Concurrent Rebuild Remembered Sets 3.433ms
		[1.141s][info][gc,start      ] GC(37) Pause Cleanup
		[1.141s][info][gc            ] GC(37) Pause Cleanup 587M->587M(1024M) 0.446ms
		[1.142s][info][gc,cpu        ] GC(37) User=0.00s Sys=0.00s Real=0.00s
		[1.142s][info][gc,marking    ] GC(37) Concurrent Cleanup for Next Mark
		[1.143s][info][gc,marking    ] GC(37) Concurrent Cleanup for Next Mark 1.185ms
		[1.143s][info][gc            ] GC(37) Concurrent Cycle 23.735ms
		执行结束!共生成对象次数:16231
		[1.151s][info][gc,heap,exit  ] Heap
		[1.151s][info][gc,heap,exit  ]  garbage-first heap   total 1048576K, used 600797K [0x00000000c0000000, 0x0000000100000000)
		[1.152s][info][gc,heap,exit  ]   region size 1024K, 5 young (5120K), 4 survivors (4096K)
		[1.152s][info][gc,heap,exit  ]  Metaspace       used 4979K, capacity 5037K, committed 5120K, reserved 1056768K
		[1.152s][info][gc,heap,exit  ]   class space    used 418K, capacity 444K, committed 512K, reserved 1048576K
     
