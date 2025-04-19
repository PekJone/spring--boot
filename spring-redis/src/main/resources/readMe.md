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
