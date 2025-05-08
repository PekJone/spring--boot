SpringCache:
   对使用缓存进行封装和抽象，通过在方法上使用annotation注解就能拿到缓存结果 解决了业务代码和缓存
代码耦合的问题，在不侵入业务代码的基础上支持缓存，让开发人员无感使用缓存；
  
1、@CacheConfig 类级别的注解，统一改类所有的缓存的前缀
  @CacheConfig(cacheName={"user"}) ：表示改类的所有缓存前缀都可以是user:: 为前缀
2、@Cacheable 方法级别的注解，用于将方法的结果缓存起来（一般配置CacheConfig 一起使用）
   @Cacheable(key="#id")
   public User findUserById(Integer id)
   以上方法调用时 先从缓存中读取数据，如果缓存中没有数据，再执行方法。最后把返回值添加到缓存中
3、@CachePut 方法级别的注解，一般用于更新缓存
   @CachePut(key="#obj.id")
   public user updateUser(User obj)
   先执行方法体，然后springCache通过返回值更新缓存
4、@CacheEvict 用于删除缓存
   @CachePut(key="#id")
   public user deleteUser(User obj)
   先执行方法体，再通过方法参数删除缓存
注解事项  
对于reids缓存 springcache 只支持String
对于Hash List set Zset 只能用RedisTemplate
对于多表查询的数据缓存，springcache不支持   多表查询智能用RedisTemplate

缓存击穿：
  针对定时更新任务的特定场景，采用主从轮询的方式来实现，
     步骤1：开辟两块缓存，A和B 定时器在更新缓存的时候，先更新B，再更新A，
     步骤2：用户先查询A 如果A没有在查询B

一亿数据 redis存取：
 方式一：哈希取余分区原理
      在Redis中，哈希取余分区（Hash Modulo Partitioning） 是一种数据分片（Sharding）策略，用于将数据分布到多个
      Redis节点上。 它的核心思想是通过对Key进行哈希计算，然后取模运算决定数据存储的位置。
      1.计算Key的哈希值
        对Key（如 user:1001）使用哈希函数（如CRC16、CRC32或MurmurHash）计算出一个整数值。
        例如：hash("user:1001") = 123456
      2.取模计算分区位置
        用哈希值对Redis节点数量（N）取模，得到目标节点索引
      3.数据存储 
        根据计算结果，将数据分配到对应的Redis节点。
     优点
         a.简单高效，计算方式简单，只需要一次哈希和取模运算，性能开销低。
         b.数据分布均匀（理想情况下）， 如果哈希函数分布均匀，数据可以较均衡地分散到各个节点，避免单节点过载。
         c.确定性映射，同一个Key总是映射到同一个节点，便于查找和缓存一致性。
     缺点
          a.节点扩容/缩容问题（高代价数据迁移）,当Redis节点数量（N）变化时（如从3台扩容到4台），hash(key) % N 
             的结果会改变，导致大量数据需要重新分配。 数据迁移成本高，影响系统可用性。
          b.热点问题（Key倾斜） ,如果某些Key访问频率极高（如热门商品ID），会导致某个节点负载过高，而其他节点空闲。
          c.节点数量受限 ,节点数量N的选择会影响数据分布均匀性，通常建议使用质数作为节点数，以减少哈希冲突。
 方式二：一致性哈希分区原理：
 方式三：哈希槽分区原理


docker create --name redis-node-1 --net host --privileged=true -v /data/redis/share/redis-node-1:/data redis:latest --cluster-enabled yes --appendonly yes --port 6381
docker create --name redis-node-2 --net host --privileged=true -v /data/redis/share/redis-node-2:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6382
docker create --name redis-node-3 --net host --privileged=true -v /data/redis/share/redis-node-3:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6383
docker create --name redis-node-4 --net host --privileged=true -v /data/redis/share/redis-node-4:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6384
docker create --name redis-node-5 --net host --privileged=true -v /data/redis/share/redis-node-5:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6385
docker create --name redis-node-6 --net host --privileged=true -v /data/redis/share/redis-node-6:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6386


docker exec -it redis-node-1 /bin/bash
分配主从
redis-cli --cluster create 139.196.72.239:6381   139.196.72.239:6382  139.196.72.239:6383  139.196.72.239:6384  139.196.72.239:6385  139.196.72.239:6386 --cluster-replicas 1

>>> Performing hash slots allocation on 6 nodes...
Master[0] -> Slots 0 - 5460  #给主节点分配槽的位数
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
Adding replica 139.196.72.239:6385 to 139.196.72.239:6381  #后面位主节点 前面位从节点
Adding replica 139.196.72.239:6386 to 139.196.72.239:6382
Adding replica 139.196.72.239:6384 to 139.196.72.239:6383
> [WARNING] Some slaves are in the same host as their master
M: f190d64c080c283de3b52568c4cfd2f8b316c173 139.196.72.239:6381
slots:[0-5460] (5461 slots) master   #给主节点分配槽的位数
M: ba463e7420b321b8f31bbe3b4187cf5ee3e8780d 139.196.72.239:6382
slots:[5461-10922] (5462 slots) master
M: 4711f07838b42927271e72f9a31cf0eabb3b5040 139.196.72.239:6383
slots:[10923-16383] (5461 slots) master
S: 4aabee3b2511daea1b35a8ec47b6b16ffa650b79 139.196.72.239:6384
replicates ba463e7420b321b8f31bbe3b4187cf5ee3e8780d  #从节点复制了主节点
S: 7f32e7d0767b4cec41301c3bc61c974669ada701 139.196.72.239:6385
replicates 4711f07838b42927271e72f9a31cf0eabb3b5040
S: 1266810468c624789f50c52d610984a5f256d445 139.196.72.239:6386
replicates f190d64c080c283de3b52568c4cfd2f8b316c173
Can I set the above configuration? (type 'yes' to accept): yes  #是否同意以上配置 选择yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
....
>>> Performing Cluster Check (using node 139.196.72.239:6381)
M: f190d64c080c283de3b52568c4cfd2f8b316c173 139.196.72.239:6381
slots:[0-5460] (5461 slots) master
1 additional replica(s)
M: ba463e7420b321b8f31bbe3b4187cf5ee3e8780d 139.196.72.239:6382
slots:[5461-10922] (5462 slots) master
1 additional replica(s)
S: 4aabee3b2511daea1b35a8ec47b6b16ffa650b79 139.196.72.239:6384
slots: (0 slots) slave
replicates ba463e7420b321b8f31bbe3b4187cf5ee3e8780d
S: 1266810468c624789f50c52d610984a5f256d445 139.196.72.239:6386
slots: (0 slots) slave
replicates f190d64c080c283de3b52568c4cfd2f8b316c173
S: 7f32e7d0767b4cec41301c3bc61c974669ada701 139.196.72.239:6385
slots: (0 slots) slave
replicates 4711f07838b42927271e72f9a31cf0eabb3b5040
M: 4711f07838b42927271e72f9a31cf0eabb3b5040 139.196.72.239:6383
slots:[10923-16383] (5461 slots) master
1 additional replica(s)
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
进入容器：  redis-cli -h 139.196.72.239 -p 6381 -c 
> 查看集群状态：cluster info
> 
> cluster_state:ok  集群状态
cluster_slots_assigned:16384  分配槽的位数
 cluster_slots_ok:16384  准确分配的槽位
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6  当前接点数
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:1
cluster_stats_messages_ping_sent:461
cluster_stats_messages_pong_sent:436
cluster_stats_messages_sent:897
cluster_stats_messages_ping_received:431
cluster_stats_messages_pong_received:461
cluster_stats_messages_meet_received:5
cluster_stats_messages_received:897
cluster nodes  查看接点状态



添加接点： redis-cli --cluster add-node  139.196.72.239:6387 139.196.72.239:6381
第一个接点是需要加入的节点  第二个节点是原有的节点 

重新分配槽点：redis-cli --cluster reshard ip:port  输入任何一个节点即可 


