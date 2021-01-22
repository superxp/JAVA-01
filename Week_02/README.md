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
      
		[0.019s][info][gc] Using Parallel
		[0.021s][info][gc,heap,coops] Heap address: 0x00000000e0000000, size: 512 MB, Compressed Oops mode: 32-bit
		正在执行...
		[0.176s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
		[0.186s][info][gc,heap      ] GC(0) PSYoungGen: 131584K->21498K(153088K)
		[0.186s][info][gc,heap      ] GC(0) ParOldGen: 0K->24188K(349696K)
		[0.187s][info][gc,metaspace ] GC(0) Metaspace: 3988K->3988K(1056768K)
		[0.196s][info][gc           ] GC(0) Pause Young (Allocation Failure) 128M->44M(491M) 19.385ms
		[0.196s][info][gc,cpu       ] GC(0) User=0.00s Sys=0.00s Real=0.02s
		[0.237s][info][gc,start     ] GC(1) Pause Young (Allocation Failure)
		[0.251s][info][gc,heap      ] GC(1) PSYoungGen: 153082K->21496K(153088K)
		[0.251s][info][gc,heap      ] GC(1) ParOldGen: 24188K->68685K(349696K)
		[0.252s][info][gc,metaspace ] GC(1) Metaspace: 3988K->3988K(1056768K)
		[0.252s][info][gc           ] GC(1) Pause Young (Allocation Failure) 173M->88M(491M) 15.652ms
		[0.261s][info][gc,cpu       ] GC(1) User=0.00s Sys=0.02s Real=0.02s
		[0.279s][info][gc,start     ] GC(2) Pause Young (Allocation Failure)
		[0.291s][info][gc,heap      ] GC(2) PSYoungGen: 153026K->21498K(153088K)
		[0.292s][info][gc,heap      ] GC(2) ParOldGen: 68685K->113243K(349696K)
		[0.299s][info][gc,metaspace ] GC(2) Metaspace: 3988K->3988K(1056768K)
		[0.300s][info][gc           ] GC(2) Pause Young (Allocation Failure) 216M->131M(491M) 21.226ms
		[0.301s][info][gc,cpu       ] GC(2) User=0.05s Sys=0.00s Real=0.02s
		[0.321s][info][gc,start     ] GC(3) Pause Young (Allocation Failure)
		[0.337s][info][gc,heap      ] GC(3) PSYoungGen: 153082K->21493K(153088K)
		[0.337s][info][gc,heap      ] GC(3) ParOldGen: 113243K->162686K(349696K)
		[0.337s][info][gc,metaspace ] GC(3) Metaspace: 3988K->3988K(1056768K)
		[0.349s][info][gc           ] GC(3) Pause Young (Allocation Failure) 260M->179M(491M) 28.527ms
		[0.350s][info][gc,cpu       ] GC(3) User=0.02s Sys=0.03s Real=0.03s
		[0.420s][info][gc,start     ] GC(4) Pause Young (Allocation Failure)
		[0.454s][info][gc,heap      ] GC(4) PSYoungGen: 152981K->21489K(153088K)
		[0.455s][info][gc,heap      ] GC(4) ParOldGen: 162686K->212315K(349696K)
		[0.455s][info][gc,metaspace ] GC(4) Metaspace: 3988K->3988K(1056768K)
		[0.457s][info][gc           ] GC(4) Pause Young (Allocation Failure) 308M->228M(491M) 36.898ms
		[0.457s][info][gc,cpu       ] GC(4) User=0.06s Sys=0.05s Real=0.04s
		[0.475s][info][gc,start     ] GC(5) Pause Young (Allocation Failure)
		[0.489s][info][gc,heap      ] GC(5) PSYoungGen: 153073K->21499K(80384K)
		[0.489s][info][gc,heap      ] GC(5) ParOldGen: 212315K->264541K(349696K)
		[0.490s][info][gc,metaspace ] GC(5) Metaspace: 3988K->3988K(1056768K)
		[0.502s][info][gc           ] GC(5) Pause Young (Allocation Failure) 356M->279M(420M) 26.508ms
		[0.506s][info][gc,cpu       ] GC(5) User=0.02s Sys=0.03s Real=0.03s
		[0.542s][info][gc,start     ] GC(6) Pause Young (Allocation Failure)
		[0.553s][info][gc,heap      ] GC(6) PSYoungGen: 80379K->39576K(116736K)
		[0.554s][info][gc,heap      ] GC(6) ParOldGen: 264541K->267958K(349696K)
		[0.555s][info][gc,metaspace ] GC(6) Metaspace: 3988K->3988K(1056768K)
		[0.563s][info][gc           ] GC(6) Pause Young (Allocation Failure) 336M->300M(455M) 20.385ms
		[0.565s][info][gc,cpu       ] GC(6) User=0.05s Sys=0.02s Real=0.02s
		[0.581s][info][gc,start     ] GC(7) Pause Young (Allocation Failure)
		[0.590s][info][gc,heap      ] GC(7) PSYoungGen: 98456K->57206K(116736K)
		[0.590s][info][gc,heap      ] GC(7) ParOldGen: 267958K->273275K(349696K)
		[0.591s][info][gc,metaspace ] GC(7) Metaspace: 3988K->3988K(1056768K)
		[0.600s][info][gc           ] GC(7) Pause Young (Allocation Failure) 357M->322M(455M) 19.148ms
		[0.601s][info][gc,cpu       ] GC(7) User=0.00s Sys=0.00s Real=0.02s
		[0.617s][info][gc,start     ] GC(8) Pause Young (Allocation Failure)
		[0.632s][info][gc,heap      ] GC(8) PSYoungGen: 116075K->57837K(116736K)
		[0.633s][info][gc,heap      ] GC(8) ParOldGen: 273275K->292497K(349696K)
		[0.633s][info][gc,metaspace ] GC(8) Metaspace: 3988K->3988K(1056768K)
		[0.634s][info][gc           ] GC(8) Pause Young (Allocation Failure) 380M->342M(455M) 17.168ms
		[0.634s][info][gc,cpu       ] GC(8) User=0.05s Sys=0.00s Real=0.02s
		[0.635s][info][gc,start     ] GC(9) Pause Full (Ergonomics)
		[0.635s][info][gc,phases,start] GC(9) Marking Phase
		[0.638s][info][gc,phases      ] GC(9) Marking Phase 3.530ms
		[0.639s][info][gc,phases,start] GC(9) Summary Phase
		[0.639s][info][gc,phases      ] GC(9) Summary Phase 0.233ms
		[0.639s][info][gc,phases,start] GC(9) Adjust Roots
		[0.640s][info][gc,phases      ] GC(9) Adjust Roots 0.946ms
		[0.640s][info][gc,phases,start] GC(9) Compaction Phase
		[0.675s][info][gc,phases      ] GC(9) Compaction Phase 34.416ms
		[0.675s][info][gc,phases,start] GC(9) Post Compact
		[0.678s][info][gc,phases      ] GC(9) Post Compact 2.442ms
		[0.678s][info][gc,heap        ] GC(9) PSYoungGen: 57837K->0K(116736K)
		[0.678s][info][gc,heap        ] GC(9) ParOldGen: 292497K->244363K(349696K)
		[0.678s][info][gc,metaspace   ] GC(9) Metaspace: 3988K->3988K(1056768K)
		[0.679s][info][gc             ] GC(9) Pause Full (Ergonomics) 342M->238M(455M) 44.082ms
		[0.679s][info][gc,cpu         ] GC(9) User=0.11s Sys=0.00s Real=0.04s
		[0.687s][info][gc,start       ] GC(10) Pause Young (Allocation Failure)
		[0.690s][info][gc,heap        ] GC(10) PSYoungGen: 58880K->20656K(116736K)
		[0.690s][info][gc,heap        ] GC(10) ParOldGen: 244363K->244363K(349696K)
		[0.691s][info][gc,metaspace   ] GC(10) Metaspace: 3988K->3988K(1056768K)
		[0.691s][info][gc             ] GC(10) Pause Young (Allocation Failure) 296M->258M(455M) 3.563ms
		[0.691s][info][gc,cpu         ] GC(10) User=0.00s Sys=0.02s Real=0.00s
		[0.700s][info][gc,start       ] GC(11) Pause Young (Allocation Failure)
		[0.706s][info][gc,heap        ] GC(11) PSYoungGen: 79290K->21813K(116736K)
		[0.706s][info][gc,heap        ] GC(11) ParOldGen: 244363K->264136K(349696K)
		[0.707s][info][gc,metaspace   ] GC(11) Metaspace: 3988K->3988K(1056768K)
		[0.707s][info][gc             ] GC(11) Pause Young (Allocation Failure) 316M->279M(455M) 6.396ms
		[0.707s][info][gc,cpu         ] GC(11) User=0.06s Sys=0.00s Real=0.01s
		[0.716s][info][gc,start       ] GC(12) Pause Young (Allocation Failure)
		[0.724s][info][gc,heap        ] GC(12) PSYoungGen: 80693K->20739K(116736K)
		[0.724s][info][gc,heap        ] GC(12) ParOldGen: 264136K->284734K(349696K)
		[0.724s][info][gc,metaspace   ] GC(12) Metaspace: 3988K->3988K(1056768K)
		[0.724s][info][gc             ] GC(12) Pause Young (Allocation Failure) 336M->298M(455M) 8.082ms
		[0.725s][info][gc,cpu         ] GC(12) User=0.06s Sys=0.00s Real=0.01s
		[0.734s][info][gc,start       ] GC(13) Pause Young (Allocation Failure)
		[0.740s][info][gc,heap        ] GC(13) PSYoungGen: 79619K->19468K(116736K)
		[0.740s][info][gc,heap        ] GC(13) ParOldGen: 284734K->304648K(349696K)
		[0.740s][info][gc,metaspace   ] GC(13) Metaspace: 3988K->3988K(1056768K)
		[0.741s][info][gc             ] GC(13) Pause Young (Allocation Failure) 355M->316M(455M) 6.799ms
		[0.741s][info][gc,cpu         ] GC(13) User=0.06s Sys=0.00s Real=0.01s
		[0.741s][info][gc,start       ] GC(14) Pause Full (Ergonomics)
		[0.741s][info][gc,phases,start] GC(14) Marking Phase
		[0.743s][info][gc,phases      ] GC(14) Marking Phase 1.350ms
		[0.743s][info][gc,phases,start] GC(14) Summary Phase
		[0.743s][info][gc,phases      ] GC(14) Summary Phase 0.165ms
		[0.743s][info][gc,phases,start] GC(14) Adjust Roots
		[0.744s][info][gc,phases      ] GC(14) Adjust Roots 0.684ms
		[0.744s][info][gc,phases,start] GC(14) Compaction Phase
		[0.777s][info][gc,phases      ] GC(14) Compaction Phase 33.429ms
		[0.778s][info][gc,phases,start] GC(14) Post Compact
		[0.779s][info][gc,phases      ] GC(14) Post Compact 0.896ms
		[0.779s][info][gc,heap        ] GC(14) PSYoungGen: 19468K->0K(116736K)
		[0.779s][info][gc,heap        ] GC(14) ParOldGen: 304648K->267026K(349696K)
		[0.779s][info][gc,metaspace   ] GC(14) Metaspace: 3988K->3988K(1056768K)
		[0.780s][info][gc             ] GC(14) Pause Full (Ergonomics) 316M->260M(455M) 38.571ms
		[0.780s][info][gc,cpu         ] GC(14) User=0.09s Sys=0.02s Real=0.04s
		[0.788s][info][gc,start       ] GC(15) Pause Young (Allocation Failure)
		[0.792s][info][gc,heap        ] GC(15) PSYoungGen: 58880K->21118K(116736K)
		[0.792s][info][gc,heap        ] GC(15) ParOldGen: 267026K->267026K(349696K)
		[0.793s][info][gc,metaspace   ] GC(15) Metaspace: 3988K->3988K(1056768K)
		[0.793s][info][gc             ] GC(15) Pause Young (Allocation Failure) 318M->281M(455M) 4.833ms
		[0.793s][info][gc,cpu         ] GC(15) User=0.00s Sys=0.00s Real=0.01s
		[0.801s][info][gc,start       ] GC(16) Pause Young (Allocation Failure)
		[0.807s][info][gc,heap        ] GC(16) PSYoungGen: 79875K->22961K(116736K)
		[0.807s][info][gc,heap        ] GC(16) ParOldGen: 267026K->287927K(349696K)
		[0.808s][info][gc,metaspace   ] GC(16) Metaspace: 3988K->3988K(1056768K)
		[0.808s][info][gc             ] GC(16) Pause Young (Allocation Failure) 338M->303M(455M) 6.414ms
		[0.808s][info][gc,cpu         ] GC(16) User=0.00s Sys=0.00s Real=0.01s
		[0.817s][info][gc,start       ] GC(17) Pause Young (Allocation Failure)
		[0.824s][info][gc,heap        ] GC(17) PSYoungGen: 81658K->21422K(116736K)
		[0.824s][info][gc,heap        ] GC(17) ParOldGen: 287927K->308220K(349696K)
		[0.824s][info][gc,metaspace   ] GC(17) Metaspace: 3988K->3988K(1056768K)
		[0.825s][info][gc             ] GC(17) Pause Young (Allocation Failure) 360M->321M(455M) 7.407ms
		[0.825s][info][gc,cpu         ] GC(17) User=0.00s Sys=0.00s Real=0.01s
		[0.825s][info][gc,start       ] GC(18) Pause Full (Ergonomics)
		[0.825s][info][gc,phases,start] GC(18) Marking Phase
		[0.827s][info][gc,phases      ] GC(18) Marking Phase 1.970ms
		[0.828s][info][gc,phases,start] GC(18) Summary Phase
		[0.828s][info][gc,phases      ] GC(18) Summary Phase 0.231ms
		[0.829s][info][gc,phases,start] GC(18) Adjust Roots
		[0.829s][info][gc,phases      ] GC(18) Adjust Roots 0.576ms
		[0.829s][info][gc,phases,start] GC(18) Compaction Phase
		[0.863s][info][gc,phases      ] GC(18) Compaction Phase 33.821ms
		[0.864s][info][gc,phases,start] GC(18) Post Compact
		[0.865s][info][gc,phases      ] GC(18) Post Compact 0.898ms
		[0.865s][info][gc,heap        ] GC(18) PSYoungGen: 21422K->0K(116736K)
		[0.865s][info][gc,heap        ] GC(18) ParOldGen: 308220K->275001K(349696K)
		[0.865s][info][gc,metaspace   ] GC(18) Metaspace: 3988K->3988K(1056768K)
		[0.866s][info][gc             ] GC(18) Pause Full (Ergonomics) 321M->268M(455M) 40.331ms
		[0.866s][info][gc,cpu         ] GC(18) User=0.13s Sys=0.00s Real=0.04s
		[0.875s][info][gc,start       ] GC(19) Pause Young (Allocation Failure)
		[0.879s][info][gc,heap        ] GC(19) PSYoungGen: 58834K->21668K(116736K)
		[0.879s][info][gc,heap        ] GC(19) ParOldGen: 275001K->275001K(349696K)
		[0.879s][info][gc,metaspace   ] GC(19) Metaspace: 3988K->3988K(1056768K)
		[0.880s][info][gc             ] GC(19) Pause Young (Allocation Failure) 326M->289M(455M) 4.724ms
		[0.880s][info][gc,cpu         ] GC(19) User=0.00s Sys=0.00s Real=0.01s
		[0.887s][info][gc,start       ] GC(20) Pause Young (Allocation Failure)
		[0.893s][info][gc,heap        ] GC(20) PSYoungGen: 80461K->25989K(116736K)
		[0.894s][info][gc,heap        ] GC(20) ParOldGen: 275001K->295498K(349696K)
		[0.894s][info][gc,metaspace   ] GC(20) Metaspace: 3988K->3988K(1056768K)
		[0.894s][info][gc             ] GC(20) Pause Young (Allocation Failure) 347M->313M(455M) 7.166ms
		[0.895s][info][gc,cpu         ] GC(20) User=0.06s Sys=0.00s Real=0.01s
		[0.903s][info][gc,start       ] GC(21) Pause Young (Allocation Failure)
		[0.910s][info][gc,heap        ] GC(21) PSYoungGen: 84738K->23591K(116736K)
		[0.911s][info][gc,heap        ] GC(21) ParOldGen: 295498K->318943K(349696K)
		[0.911s][info][gc,metaspace   ] GC(21) Metaspace: 3988K->3988K(1056768K)
		[0.911s][info][gc             ] GC(21) Pause Young (Allocation Failure) 371M->334M(455M) 7.747ms
		[0.911s][info][gc,cpu         ] GC(21) User=0.00s Sys=0.06s Real=0.01s
		[0.912s][info][gc,start       ] GC(22) Pause Full (Ergonomics)
		[0.928s][info][gc,phases,start] GC(22) Marking Phase
		[0.931s][info][gc,phases      ] GC(22) Marking Phase 3.323ms
		[0.932s][info][gc,phases,start] GC(22) Summary Phase
		[0.932s][info][gc,phases      ] GC(22) Summary Phase 0.505ms
		[0.933s][info][gc,phases,start] GC(22) Adjust Roots
		[0.934s][info][gc,phases      ] GC(22) Adjust Roots 1.474ms
		[0.934s][info][gc,phases,start] GC(22) Compaction Phase
		[0.973s][info][gc,phases      ] GC(22) Compaction Phase 38.739ms
		[0.974s][info][gc,phases,start] GC(22) Post Compact
		[0.975s][info][gc,phases      ] GC(22) Post Compact 0.989ms
		[0.975s][info][gc,heap        ] GC(22) PSYoungGen: 23591K->0K(116736K)
		[0.975s][info][gc,heap        ] GC(22) ParOldGen: 318943K->294366K(349696K)
		[0.975s][info][gc,metaspace   ] GC(22) Metaspace: 3988K->3988K(1056768K)
		[0.976s][info][gc             ] GC(22) Pause Full (Ergonomics) 334M->287M(455M) 64.057ms
		[0.976s][info][gc,cpu         ] GC(22) User=0.17s Sys=0.00s Real=0.07s
		[0.984s][info][gc,start       ] GC(23) Pause Young (Allocation Failure)
		[0.987s][info][gc,heap        ] GC(23) PSYoungGen: 58880K->22483K(116736K)
		[0.988s][info][gc,heap        ] GC(23) ParOldGen: 294366K->294366K(349696K)
		[0.988s][info][gc,metaspace   ] GC(23) Metaspace: 3988K->3988K(1056768K)
		[0.988s][info][gc             ] GC(23) Pause Young (Allocation Failure) 344M->309M(455M) 3.821ms
		[0.988s][info][gc,cpu         ] GC(23) User=0.00s Sys=0.00s Real=0.00s
		[0.998s][info][gc,start       ] GC(24) Pause Young (Allocation Failure)
		[1.003s][info][gc,heap        ] GC(24) PSYoungGen: 81139K->22636K(116736K)
		[1.004s][info][gc,heap        ] GC(24) ParOldGen: 294366K->314808K(349696K)
		[1.004s][info][gc,metaspace   ] GC(24) Metaspace: 3988K->3988K(1056768K)
		[1.004s][info][gc             ] GC(24) Pause Young (Allocation Failure) 366M->329M(455M) 6.323ms
		[1.005s][info][gc,cpu         ] GC(24) User=0.06s Sys=0.00s Real=0.01s
		[1.005s][info][gc,start       ] GC(25) Pause Full (Ergonomics)
		[1.005s][info][gc,phases,start] GC(25) Marking Phase
		[1.007s][info][gc,phases      ] GC(25) Marking Phase 1.430ms
		[1.007s][info][gc,phases,start] GC(25) Summary Phase
		[1.007s][info][gc,phases      ] GC(25) Summary Phase 0.195ms
		[1.007s][info][gc,phases,start] GC(25) Adjust Roots
		[1.007s][info][gc,phases      ] GC(25) Adjust Roots 0.319ms
		[1.008s][info][gc,phases,start] GC(25) Compaction Phase
		[1.046s][info][gc,phases      ] GC(25) Compaction Phase 37.912ms
		[1.046s][info][gc,phases,start] GC(25) Post Compact
		[1.047s][info][gc,phases      ] GC(25) Post Compact 0.937ms
		[1.047s][info][gc,heap        ] GC(25) PSYoungGen: 22636K->0K(116736K)
		[1.048s][info][gc,heap        ] GC(25) ParOldGen: 314808K->297340K(349696K)
		[1.048s][info][gc,metaspace   ] GC(25) Metaspace: 3988K->3988K(1056768K)
		[1.048s][info][gc             ] GC(25) Pause Full (Ergonomics) 329M->290M(455M) 43.105ms
		[1.048s][info][gc,cpu         ] GC(25) User=0.11s Sys=0.00s Real=0.04s
		[1.057s][info][gc,start       ] GC(26) Pause Young (Allocation Failure)
		[1.061s][info][gc,heap        ] GC(26) PSYoungGen: 58264K->23312K(116736K)
		[1.061s][info][gc,heap        ] GC(26) ParOldGen: 297340K->297340K(349696K)
		[1.061s][info][gc,metaspace   ] GC(26) Metaspace: 3988K->3988K(1056768K)
		[1.061s][info][gc             ] GC(26) Pause Young (Allocation Failure) 347M->313M(455M) 4.162ms
		[1.061s][info][gc,cpu         ] GC(26) User=0.00s Sys=0.00s Real=0.00s
		[1.071s][info][gc,start       ] GC(27) Pause Young (Allocation Failure)
		[1.078s][info][gc,heap        ] GC(27) PSYoungGen: 82192K->19576K(116736K)
		[1.088s][info][gc,heap        ] GC(27) ParOldGen: 297340K->320309K(349696K)
		[1.088s][info][gc,metaspace   ] GC(27) Metaspace: 3988K->3988K(1056768K)
		[1.089s][info][gc             ] GC(27) Pause Young (Allocation Failure) 370M->331M(455M) 17.381ms
		[1.089s][info][gc,cpu         ] GC(27) User=0.00s Sys=0.00s Real=0.02s
		[1.089s][info][gc,start       ] GC(28) Pause Full (Ergonomics)
		[1.089s][info][gc,phases,start] GC(28) Marking Phase
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



