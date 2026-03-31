# 🏋️ Lộ Trình Training Spring Boot — Middle Level

> **Thời lượng:** 30 ngày (~5 tuần) | **Mỗi ngày:** 2–4 giờ
>
> **Nguyên tắc:** Lý thuyết (30%) + Thực hành code (70%)
> **Thư mục làm việc:** `/Users/chung/workspace/spring_trainning/projects/`
>
> **Trước khi bắt đầu — chuẩn bị:**
> - [ ] Java 17+
> - [ ] IntelliJ IDEA (Ultimate hoặc Community)
> - [ ] Docker Desktop (chạy RabbitMQ, Kafka, Redis, MySQL)
> - [ ] Tạo thư mục `projects/` trong `/Users/chung/workspace/spring_trainning/`
> - [ ] Mỗi ngày tạo thư mục con: `day01/`, `day02/`…

---

## 📅 TUẦN 1: Nền tảng Spring Boot

---

### 🌅 Ngày 1: Bean Lifecycle & Scopes

**Lý thuyết (1h):**
- [ ] Đọc: Bean lifecycle — initialization callback → dependency injection → destroy callback
- [ ] Hiểu `@PostConstruct` vs `InitializingBean.afterPropertiesSet()` vs `@Bean(initMethod=...)`
- [ ] Hiểu `@PreDestroy` vs `DisposableBean.destroy()` vs `@Bean(destroyMethod=...)`
- [ ] Các Bean scopes: `singleton`, `prototype`, `request`, `session`, `application`, `websocket`

**Thực hành (2h):**
- [ ] Tạo project `day01-bean-lifecycle` với Spring Boot 3.x
- [ ] Viết class `ProductService` implement `InitializingBean`, `DisposableBean`
- [ ] Thêm `@PostConstruct init()` và `@PreDestroy cleanup()`
- [ ] Tạo 2 bean cùng interface: `singleton` và `prototype` — inject vào controller để kiểm tra behavior khác nhau
- [ ] Dùng `ConfigurableBeanFactory` để programmatically đăng ký bean
- [ ] Viết unit test kiểm tra bean được khởi tạo đúng thứ tự

**Output:** `projects/day01-bean-lifecycle/src/main/java/com/example/day01/`

---

### 🌅 Ngày 2: Spring Boot Auto-Configuration

**Lý thuyết (1.5h):**
- [ ] Đọc: Spring Boot auto-configuration mechanism
- [ ] Hiểu `spring.factories` (Boot 2.x) vs `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` (Boot 3.x)
- [ ] Hiểu `@AutoConfiguration`, `@Conditional`, `@ConditionalOnClass`, `@ConditionalOnMissingBean`
- [ ] Biết cách override auto-config bằng custom bean

**Thực hành (1.5h):**
- [ ] Tạo project `day02-auto-config`
- [ ] Viết custom auto-configuration class `MyAutoConfiguration` với `@AutoConfiguration`
- [ ] Tạo file `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- [ ] Dùng `@ConditionalOnProperty` để bật/tắt config
- [ ] Viết test: khi enable property → bean tồn tại, khi disable → bean không tồn tại

**Output:** `projects/day02-auto-config/`

---

### 🌅 Ngày 3: Dependency Injection — Các loại injection

**Lý thuyết (1h):**
- [ ] Field Injection vs Setter Injection vs Constructor Injection — nên dùng gì? Tại sao?
- [ ] `@Autowired` trên constructor → spring inject all dependencies
- [ ] `Optional<T>` trong constructor — khi nào cần?
- [ ] `ObjectProvider<T>` — lazy resolution

**Thực hành (2h):**
- [ ] Tạo project `day03-di-patterns`
- [ ] Xây dựng: `UserService` (constructor DI) + `EmailService` + `SmsService`
- [ ] Inject `Optional<EmailService>` — handle khi bean không tồn tại
- [ ] Inject `ObjectProvider<NotificationService>` — getIfAvailable()
- [ ] Tạo circular dependency A→B→A → dùng `@Lazy` để fix
- [ ] Viết unit test kiểm tra DI hoạt động đúng

**Output:** `projects/day03-di-patterns/`

---

### 🌅 Ngày 4: Qualifier & Custom Annotation

**Lý thuyết (1h):**
- [ ] `@Primary` — chỉ định bean mặc định khi nhiều bean cùng loại
- [ ] `@Qualifier` — chỉ định rõ bean cụ thể
- [ ] `@Named` (JSR-330) vs `@Component` — so sánh
- [ ] Custom qualifier annotation với attribute: `@PaymentGateway(type="stripe")`

**Thực hành (2h):**
- [ ] Tạo project `day04-qualifiers`
- [ ] Tạo 3 implementation của `PaymentProcessor`: `StripeProcessor`, `PayPalProcessor`, `VNPayProcessor`
- [ ] Tạo custom annotation `@PaymentGateway("stripe")`, `@PaymentGateway("paypal")`
- [ ] Inject đúng processor bằng `@Qualifier` và custom annotation
- [ ] Inject danh sách tất cả payment processors bằng `List<PaymentProcessor>`
- [ ] Viết test: inject stripe processor → gọi process → verify được gọi

**Output:** `projects/day04-qualifiers/`

---

### 🌅 Ngày 5: FactoryBean & ImportBeanDefinitionRegistrar

**Lý thuyết (1h):**
- [ ] `FactoryBean<T>` — tạo bean bằng factory logic phức tạp
- [ ] `ImportBeanDefinitionRegistrar` — đăng ký bean definitions programmatically
- [ ] `BeanDefinitionRegistryPostProcessor` — can thiệp trước khi bean được tạo

**Thực hành (2.5h):**
- [ ] Tạo project `day05-factory-bean`
- [ ] Viết `PasswordEncoderFactory` implement `FactoryBean<PasswordEncoder>` — tạo BCrypt hoặc Argon2 tùy config
- [ ] Viết `ImportBeanDefinitionRegistrar` để đăng ký 10 data source beans từ config
- [ ] Viết `BeanDefinitionRegistryPostProcessor` log tất cả bean names trước khi khởi tạo
- [ ] Viết test kiểm tra factory bean trả về đúng loại encoder

**Output:** `projects/day05-factory-bean/`

---

### 🌅 Ngày 6: @Transactional — Proxy & Rollback

**Lý thuyết (1.5h):**
- [ ] Spring AOP proxy mechanism — JDK Dynamic Proxy vs CGLIB
- [ ] `@Transactional` không hoạt động trong self-invocation (internal call)
- [ ] Rollback rules: unchecked exception (RuntimeException) → rollback, checked → commit
- [ ] `noRollbackFor`, `rollbackFor`

**Thực hành (2h):**
- [ ] Tạo project `day06-transaction`
- [ ] Viết `OrderService` với nested method:
  - `createOrder()` — `@Transactional`
  - `processPayment()` — bên trong `createOrder()` gọi trực tiếp → proxy không apply
- [ ] Fix self-invocation bằng: inject self + dùng `TransactionTemplate`
- [ ] Test: throw RuntimeException → rollback xác nhận; throw checked Exception → commit xác nhận
- [ ] Test: `@Transactional(readOnly = true)` → kiểm tra Hibernate stats

**Output:** `projects/day06-transaction/`

---

### 🌅 Ngày 7: Transaction Propagation

**Lý thuyết (1h):**
- [ ] 7 propagation levels: REQUIRED, SUPPORTS, MANDATORY, REQUIRES_NEW, NOT_SUPPORTED, NEVER, NESTED
- [ ] `NESTED` vs `REQUIRES_NEW` — savepoint trong same transaction vs tạo transaction mới
- [ ] Khi nào dùng `REQUIRES_NEW` (log audit) vs `NESTED` (compensation pattern)

**Thực hành (2.5h):**
- [ ] Mở rộng project `day06-transaction`
- [ ] Viết `AccountService` với 3 methods:
  - `transfer()` — `@Transactional(propagation = REQUIRED)`
  - `logAudit()` — `@Transactional(propagation = REQUIRES_NEW)`
  - `reverseTransaction()` — `@Transactional(propagation = NESTED)`
- [ ] Viết integration test: `transfer()` gọi `logAudit()` → commit cả 2; `transfer()` fail → audit log vẫn được giữ (REQUIRES_NEW)
- [ ] Log SQL để thấy rõ transaction boundary

**Output:** `projects/day06-transaction/` (mở rộng)

---

### 🌅 Ngày 8: Isolation Levels & Concurrency Problems

**Lý thuyết (1.5h):**
- [ ] 4 isolation levels: READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
- [ ] 4 concurrency problems: dirty read, non-repeatable read, phantom read, lost update
- [ ] `READ_COMMITTED` vs `REPEATABLE_READ` — Oracle default vs MySQL default
- [ ] Pessimistic locking (`SELECT FOR UPDATE`) vs Optimistic locking (`@Version`)

**Thực hành (2h):**
- [ ] Tạo project `day08-isolation`
- [ ] Entity `Account` với `@Version` — optimistic locking
- [ ] Viết `AccountService.updateBalance()` — concurrent update → catch `OptimisticLockException`
- [ ] Viết `AccountService.lockAccount()` dùng `@Lock(LockModeType.PESSIMISTIC_WRITE)`
- [ ] Viết test: 2 threads cùng update → optimistic: 1 thắng, 1 throw; pessimistic: sequential execution
- [ ] So sánh performance: optimistic vs pessimistic

**Output:** `projects/day08-isolation/`

---

## 📅 TUẦN 2: JPA / Hibernate + Query

---

### 🌅 Ngày 9: Entity Lifecycle & State Transitions

**Lý thuyết (1h):**
- [ ] 4 entity states: transient, persistent, detached, removed
- [ ] `EntityManager.persist()` vs `merge()` vs `remove()`
- [ ] Cascade types: PERSIST, MERGE, REMOVE, REFRESH, ALL
- [ ] `orphanRemoval = true`

**Thực hành (2.5h):**
- [ ] Tạo project `day09-entity-lifecycle`
- [ ] Entity `Author` (1) ↔ `Book` (N) với cascade ALL + orphanRemoval
- [ ] Viết `AuthorRepository` test các scenario:
  - Save author + book → persist both (cascade)
  - Load author → remove book → orphanRemoval tự xóa
  - Detach → modify → merge
  - Clear persistence context → dirty check behavior
- [ ] Bật Hibernate SQL log → xem INSERT/UPDATE/DELETE

**Output:** `projects/day09-entity-lifecycle/`

---

### 🌅 Ngày 10: N+1 Problem & Fetching Strategies

**Lý thuyết (1.5h):**
- [ ] N+1 queries: 1 query lấy N entities + N queries lấy related
- [ ] `LAZY` vs `EAGER` — rủi ro của EAGER
- [ ] `@BatchSize(size=25)` — giảm N queries → ceil(N/25)
- [ ] `@Fetch(FetchMode.SUBSELECT)` — dùng subquery thay vì N queries
- [ ] `@NamedEntityGraph`, `@EntityGraph` — dynamic fetch

**Thực hành (2h):**
- [ ] Tạo project `day10-n-plus-one`
- [ ] Tạo 3 entities: `Store` → `Product` → `Category`
- [ ] Insert 5 stores, mỗi store 20 products
- [ ] Query all stores:
  - Version 1: KHÔNG optimize → count SQL queries
  - Version 2: Thêm `@BatchSize` → count lại
  - Version 3: Dùng `@EntityGraph` → count lại
- [ ] In ra log: "Before optimization: X queries. After: Y queries"

**Output:** `projects/day10-n-plus-one/`

---

### 🌅 Ngày 11: JPA Projection & DTO Mapping

**Lý thuyết (1h):**
- [ ] Interface projection: `interface UserSummary { String getName(); }`
- [ ] Class projection (DTO): `class UserDTO`
- [ ] Dynamic projection với `JpaRepository<T, ID>` + `<projectionClass>`
- [ ] MapStruct — auto mapping entity ↔ DTO

**Thực hành (2.5h):**
- [ ] Tạo project `day11-projection`
- [ ] Entity `Employee` với 15 fields
- [ ] Tạo 3 loại projection:
  - Interface: `EmployeeNameOnly` { getName(), getDepartment() }
  - DTO: `EmployeeSummary` (10 fields)
  - Class: `EmployeeFull` (all fields)
- [ ] Benchmark: query với interface projection vs full entity
- [ ] Tích hợp MapStruct: `EmployeeMapper` auto map entity ↔ DTO
- [ ] Viết test: projection trả về đúng data, không có field thừa

**Output:** `projects/day11-projection/`

---

### 🌅 Ngày 12: JPA Specification Pattern

**Lý thuyết (1h):**
- [ ] Specification pattern — clean, reusable filter logic
- [ ] `Specification<T>` = `Predicate<T>` trong JPA
- [ ] `JpaSpecificationExecutor<T>` — execute spec
- [ ] Combine specs: `and()`, `or()`, `not()`

**Thực hành (2.5h):**
- [ ] Tạo project `day12-specification`
- [ ] Entity `Product` với fields: name, price, category, brand, status, createdDate
- [ ] Viết specifications:
  - `ProductSpecification.hasCategory(String category)`
  - `ProductSpecification.priceBetween(BigDecimal min, BigDecimal max)`
  - `ProductSpecification.createdAfter(LocalDate date)`
  - `ProductSpecification.hasAnyBrand(List<String> brands)`
- [ ] Combine: "category=electronics AND price between 100-500 AND brand IN (apple, samsung)"
- [ ] Viết query với JOIN fetch để avoid N+1 khi filter
- [ ] Viết test: filter đúng, kết quả chính xác

**Output:** `projects/day12-specification/`

---

### 🌅 Ngày 13: Paging, Sorting & Query Hints

**Lý thuyết (1h):**
- [ ] `Pageable`, `Page`, `Slice`, `List` — phân biệt
- [ ] `Sort.by()` — multi-column sort
- [ ] `QueryHints` — read-only, fetch size, comment
- [ ] Keyset pagination (cursor-based) vs OFFSET — performance ở page lớn

**Thực hành (2.5h):**
- [ ] Mở rộng `day12-specification`
- [ ] Insert 10,000 records vào DB
- [ ] API: `GET /products?page=0&size=20&sort=price,desc&category=electronics`
- [ ] Implement keyset pagination cho "load more" feature
- [ ] Benchmark: OFFSET 5000 vs keyset pagination — thời gian query
- [ ] Thêm `@QueryHint(name = "org.hibernate.readOnly", value = "true")` cho read-only query
- [ ] Viết test: paging metadata đúng (totalPages, totalElements, size)

**Output:** `projects/day12-specification/` (mở rộng)

---

### 🌅 Ngày 14: Auditing & Soft Deletes

**Lý thuyết (1h):**
- [ ] JPA Auditing: `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`
- [ ] `AbstractAuditEntity` — base class cho tất cả entities
- [ ] Soft delete: `deleted = false` filter mặc định, `@EntityListeners`

**Thực hành (2.5h):**
- [ ] Tạo project `day14-auditing`
- [ ] Tạo `BaseEntity` với `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`
- [ ] Cấu hình `AuditingEntityListener` + `@EnableJpaAuditing`
- [ ] Tạo `AuditingAwareImpl` implement `AuditingAware<String>` để set current user
- [ ] Implement soft delete:
  - Annotation `@SoftDelete`
  - `EntityListener` tự động convert `delete()` → set `deletedAt = now()`
  - Override `JpaRepository.delete()` → gọi soft delete
- [ ] Viết test: findById bỏ qua deleted records, có thể query all bao gồm deleted

**Output:** `projects/day14-auditing/`

---

## 📅 TUẦN 3: Redis + Message Queue

---

### 🌅 Ngày 15: Redis — Cấu hình & Data Types

**Lý thuyết (1.5h):**
- [ ] Redis 7 data types: String, Hash, List, Set, Sorted Set, Bitmap, HyperLogLog, Stream
- [ ] `RedisTemplate` vs `StringRedisTemplate` vs `RedisTemplate<K, V>` với custom serializer
- [ ] Serialize: StringSerializer, JdkSerializer, GenericJackson2JsonRedisSerializer
- [ ] TTL, EXPIRE, PERSIST

**Thực hành (2h):**
- [ ] Tạo project `day15-redis-basics`, thêm `spring-boot-starter-data-redis`
- [ ] Docker: chạy Redis `docker run -d -p 6379:6379 redis:7`
- [ ] CRUD operations với String, Hash, List, Set
- [ ] Viết `RedisConfig`:
  - `RedisTemplate<String, Object>` với GenericJackson2
  - `StringRedisTemplate` cho String-only
  - Custom serializer cho LocalDateTime
- [ ] Viết test: set → get → TTL check → delete
- [ ] Benchmark: serialize JSON vs JDK serialization

**Output:** `projects/day15-redis-basics/`

---

### 🌅 Ngày 16: Redis — Cache-Aside Pattern

**Lý thuyết (1h):**
- [ ] Cache-aside pattern: read → cache miss → load from DB → write to cache
- [ ] Write-through vs Write-behind vs Cache-aside
- [ ] Cache eviction: TTL, LRU, explicit evict
- [ ] Cache stampede (thundering herd) — dùng distributed lock để prevent

**Thực hành (2.5h):**
- [ ] Tạo project `day16-cache-aside`
- [ ] Entity `Product`, `ProductRepository`
- [ ] Implement CacheService:
  - `getProduct(id)`: check cache → miss → DB → write cache → return
  - `updateProduct()`: update DB → invalidate cache
  - `deleteProduct()`: delete DB → evict cache
- [ ] Thêm distributed lock (Redis `SETNX`) để prevent cache stampede
- [ ] Viết test: cache hit → no DB query; cache miss → 1 DB query; update → cache evicted
- [ ] Log số lượng queries để verify cache hoạt động

**Output:** `projects/day16-cache-aside/`

---

### 🌅 Ngày 17: Redis — Session, Lock & Rate Limiter

**Lý thuyết (1h):**
- [ ] Redis Session store — scale stateless app
- [ ] Distributed lock: `SET key value NX PX timeout` + Lua script
- [ ] Rate limiter: sliding window counter vs token bucket
- [ ] Redisson — library cung cấp distributed lock ready

**Thực hành (2.5h):**
- [ ] Mở rộng `day16-cache-aside` → `day17-redis-advanced`
- [ ] **Task 1 — Session:** Cấu hình Spring Session Redis — `HttpSession` tự động lưu vào Redis
- [ ] **Task 2 — Distributed Lock:** Viết `RedisDistributedLock` class:
  - `lock(key, waitTime, leaseTime)` → boolean
  - `unlock(key)`
  - Dùng Lua script để atomic unlock
- [ ] **Task 3 — Rate Limiter:** Viết `SlidingWindowRateLimiter`:
  - Key: `rate:user:{userId}`
  - Window: 60 giây, limit: 100 requests
  - Dùng ZSET (sorted set) để implement sliding window
- [ ] Viết test concurrency: 1000 requests đồng thời → verify rate limit chính xác

**Output:** `projects/day17-redis-advanced/`

---

### 🌅 Ngày 18: Redis — Pub/Sub

**Lý thuyết (1h):**
- [ ] Pub/Sub: Publisher → Channel → Subscriber
- [ ] Channel types: regular channel, pattern channel (wildcard `news.*`)
- [ ] Limitation: messages không persistent, fire-and-forget
- [ ] Redis Streams vs Pub/Sub: when to use which

**Thực hành (2.5h):**
- [ ] Mở rộng `day17-redis-advanced` → `day18-redis-pubsub`
- [ ] **Task 1:** Viết `NotificationPublisher` — publish notification event vào channel `notifications`
- [ ] **Task 2:** Viết 2 subscribers:
  - `EmailNotificationListener` — xử lý type=EMAIL
  - `SmsNotificationListener` — xử lý type=SMS
- [ ] **Task 3:** Pattern subscription — subscribe `notifications.user.{userId}`
- [ ] **Task 4:** Streaming event:
  - Tạo Stream `order-events`
  - Producer: khi order status thay đổi → ghi vào stream
  - Consumer: `XREADGROUP` với consumer group — simulation message queue
- [ ] Viết test: publish 10 messages → assert mỗi subscriber nhận đúng số lượng

**Output:** `projects/day18-redis-pubsub/`

---

### 🌅 Ngày 19: RabbitMQ — Cơ bản

**Lý thuyết (1.5h):**
- [ ] RabbitMQ architecture: Producer → Exchange → Binding → Queue → Consumer
- [ ] 4 Exchange types: direct, fanout, topic, headers
- [ ] Queue properties: durable, exclusive, auto-delete, TTL, max-length
- [ ] Dead Letter Queue (DLQ): khi message bị reject hoặc timeout → vào DLQ
- [ ] Message acknowledgment: auto ACK vs manual ACK

**Thực hành (2h):**
- [ ] Tạo project `day19-rabbitmq-basics`
- [ ] Docker: `docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:3-management`
- [ ] Cấu hình `RabbitMQConfig`:
  - Exchange: `order.exchange` (topic)
  - Queue: `order.created.queue`, `order.payment.queue`
  - Binding: `order.created.queue` ← `order.exchange` với routing key `order.created`
  - DLQ: `order.created.dlq`
- [ ] Producer: `OrderService` gửi event khi order được tạo
- [ ] Consumer: 2 listeners cho 2 queues
- [ ] Viết test: send message → verify consumer nhận đúng message

**Output:** `projects/day19-rabbitmq-basics/`

---

### 🌅 Ngày 20: RabbitMQ — Retry, DLQ & Error Handling

**Lý thuyết (1h):**
- [ ] Retry mechanism: `spring-retry` + `@Retryable`
- [ ] Exponential backoff với RabbitMQ
- [ ] Global error handler: `ErrorHandler`, `RejectedExecutionHandler`
- [ ] Message idempotency: duplicate detection

**Thực hành (2.5h):**
- [ ] Mở rộng `day19-rabbitmq-basics` → `day20-rabbitmq-advanced`
- [ ] **Task 1:** Producer retry:
  - Khi publish fails → retry 3 lần với exponential backoff
  - Dùng `RabbitTemplate.setMandatory(true)` + `ReturnCallback`
- [ ] **Task 2:** Consumer retry + DLQ:
  - `@RabbitListener` với `defaultRequeueRejected = false`
  - Message fail → vào DLQ sau 3 retry
  - DLQ consumer → log + alert
- [ ] **Task 3:** Idempotent consumer:
  - Mỗi message có `messageId`
  - Lưu vào Redis: `processed:{messageId}` với TTL
  - Check trước khi xử lý
- [ ] Viết test: message gửi 3 lần duplicate → chỉ xử lý 1 lần

**Output:** `projects/day20-rabbitmq-advanced/`

---

### 🌅 Ngày 21: Kafka — Producer & Consumer

**Lý thuyết (1.5h):**
- [ ] Kafka vs RabbitMQ: log-based vs queue-based
- [ ] Topic, Partition, Replication Factor, Offset
- [ ] Consumer Group: nhiều consumers đọc song song từ các partitions
- [ ] Exactly-once semantics: `enable.idempotence=true`
- [ ] At-least-once: manual commit offset

**Thực hành (2h):**
- [ ] Tạo project `day21-kafka-basics`
- [ ] Docker: `docker-compose` chạy Kafka + Zookeeper
- [ ] Producer: `OrderEventProducer`
  - Topic: `order-events`
  - Key: `orderId` (đảm bảo order events cùng order → cùng partition)
  - Value: JSON event `{orderId, eventType, amount, timestamp}`
- [ ] Consumer: `OrderAnalyticsConsumer`
  - Consumer group: `analytics-service`
  - Manual commit: `Acknowledgment.ack()`
  - Process: extract metrics → log
- [ ] Viết test: produce 100 events → consume → verify count

**Output:** `projects/day21-kafka-basics/`

---

### 🌅 Ngày 22: Kafka — Error Handling & Transaction

**Lý thuyết (1h):**
- [ ] Kafka consumer error handling: retry, skip, dead-letter topic
- [ ] Idempotent producer + transactional consumer
- [ ] Exactly-once: `spring.kafka.listener.subscription-mode=ack-mode=manual`
- [ ] Schema Registry + Avro/JSON Schema

**Thực hành (2.5h):**
- [ ] Mở rộng `day21-kafka-basics` → `day22-kafka-advanced`
- [ ] **Task 1:** Kafka transaction:
  - `KafkaTemplate.executeInTransaction()` — produce message + DB update atomic
  - DB rollback → message không được produce
- [ ] **Task 2:** Consumer error handling:
  - Retry 3 lần, sau đó gửi vào dead-letter topic `order-events.DLT`
  - Dùng `CommonErrorHandler` với `FixedBackOff`
- [ ] **Task 3:** Exactly-once demo:
  - Consumer: process message + update DB
  - Commit offset chỉ khi DB update thành công
- [ ] Viết test: simulate failure at different stages → verify exactly-once behavior

**Output:** `projects/day22-kafka-advanced/`

---

## 📅 TUẦN 4: Security + Async + Testing

---

### 🌅 Ngày 23: Spring Security — Filter Chain & Authentication

**Lý thuyết (1.5h):**
- [ ] Security Filter Chain: 15 filters theo thứ tự
- [ ] `AuthenticationManager` → `ProviderManager` → `AuthenticationProvider`
- [ ] UserDetailsService — load user từ DB hoặc memory
- [ ] Password encoding: BCrypt, Argon2, PBKDF2

**Thực hành (2h):**
- [ ] Tạo project `day23-spring-security`
- [ ] Cấu hình `SecurityConfig`:
  - Stateless session (JWT-ready)
  - Public endpoints: `/api/auth/**`, `/api/public/**`
  - Protected endpoints: `/api/admin/**` → ADMIN role
- [ ] `UserDetailsService` load từ JPA entity
- [ ] BCrypt password encoder
- [ ] API: `POST /api/auth/login` → trả về mock token (chưa có JWT)
- [ ] Viết test: login success → 200, login fail → 401, access protected → 403

**Output:** `projects/day23-spring-security/`

---

### 🌅 Ngày 24: JWT — Token & Authorization

**Lý thuyết (1h):**
- [ ] JWT structure: Header, Payload, Signature
- [ ] Access token (short-lived: 15min) vs Refresh token (long-lived: 7 days)
- [ ] Token blacklist — revoke token khi logout
- [ ] `@PreAuthorize`, `@Secured`, method-level security

**Thực hành (2.5h):**
- [ ] Mở rộng `day23-spring-security` → `day24-jwt`
- [ ] **Task 1:** JWT Service:
  - `generateAccessToken(UserDetails)` — HS256, 15min expiry
  - `generateRefreshToken(UserDetails)` — 7 days expiry
  - `validateToken(token)` — verify signature + expiry
  - `extractUsername(token)`
- [ ] **Task 2:** JWT Filter: `OncePerRequestFilter` — extract token từ Authorization header → validate → set SecurityContext
- [ ] **Task 3:** Refresh token:
  - Lưu refresh token vào Redis với TTL
  - `POST /api/auth/refresh` → issue new access token
  - `POST /api/auth/logout` → invalidate refresh token (Redis delete)
- [ ] **Task 4:** Method security:
  - `@PreAuthorize("hasRole('ADMIN')")` trên method
  - `@PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")`
- [ ] Viết test: JWT decode đúng, expired token → throw, refresh flow hoàn chỉnh

**Output:** `projects/day24-jwt/`

---

### 🌅 Ngày 25: @Async & CompletableFuture

**Lý thuyết (1h):**
- [ ] `@Async` — chạy method trong thread pool riêng
- [ ] `ThreadPoolTaskExecutor` configuration: corePoolSize, maxPoolSize, queueCapacity
- [ ] `CompletableFuture` — chain async operations: thenApply, thenCompose, thenCombine
- [ ] Exception handling: `exceptionally()`, `handle()`, `whenComplete()`

**Thực hành (2.5h):**
- [ ] Tạo project `day25-async`
- [ ] Cấu hình `AsyncConfig`:
  - ThreadPoolTaskExecutor cho general tasks: 10 threads
  - ThreadPoolTaskExecutor cho IO tasks: 50 threads
- [ ] Viết `OrderService`:
  - `processOrderAsync(orderId)` → `@Async` — gọi payment, inventory, notification song song
  - Dùng `CompletableFuture.allOf()` để join tất cả
- [ ] Viết `NotificationService`:
  - `sendEmail()` → CompletableFuture
  - `sendSms()` → CompletableFuture
  - `sendAll()` → combine both, retry nếu fail
- [ ] Viết test: async method → verify chạy trong thread khác (không main thread)

**Output:** `projects/day25-async/`

---

### 🌅 Ngày 26: @Scheduled & Retry

**Lý thuyết (1h):**
- [ ] `@Scheduled`: fixedDelay vs fixedRate vs cron
- [ ] `fixedRate` có thể overlap — cần semaphore để prevent
- [ ] `spring-retry`: `@Retryable`, `@Recover`
- [ ] Exponential backoff: `delay = 1000 * 2^(attempt - 1)`

**Thực hành (2.5h):**
- [ ] Mở rộng `day25-async` → `day26-scheduled-retry`
- [ ] **Task 1 — Scheduled Job:**
  - `DailyReportJob` chạy every day at 6am (`0 0 6 * * *`)
  - `CleanupJob` chạy every 30 minutes — clean expired cache entries
  - Dùng `Semaphore` để prevent overlap nếu job chạy lâu hơn interval
- [ ] **Task 2 — Retry:**
  - `PaymentGatewayService.processPayment()` — mock fail 2 lần, thành công lần 3
  - `@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2))`
  - `@Recover` — khi retry hết → gọi alternative payment
- [ ] **Task 3 — Circuit Breaker:**
  - Thêm Resilience4j: `@CircuitBreaker(failureRateThreshold = 50, waitDurationInOpenState = 30s)`
  - Demo: 5 requests fail → circuit OPEN → 30s sau → HALF_OPEN → 1 request thành công → CLOSED
- [ ] Viết test: mock payment gateway fail 2 times → assert retry called, assert recovery on 3rd attempt

**Output:** `projects/day26-scheduled-retry/`

---

### 🌅 Ngày 27: Unit Testing & MockMvc

**Lý thuyết (1h):**
- [ ] Unit test vs Integration test vs E2E test — phân biệt
- [ ] Arrange-Act-Assert (AAA) pattern
- [ ] `@Mock`, `@InjectMocks`, `@Spy` — Mockito
- [ ] `ArgumentCaptor`, `Answer` — verify complex behavior

**Thực hành (2.5h):**
- [ ] Tạo project `day27-testing-unit`
- [ ] Viết `OrderService` (logic phức tạp: pricing, discount, validation)
- [ ] Viết unit tests:
  - Happy path: valid order → total price đúng
  - Discount: order > 1000 → 10% discount
  - Empty items → throw `IllegalArgumentException`
  - `ArgumentCaptor`: verify `PaymentService.processPayment()` được gọi với đúng amount
  - `doThrow` + `doReturn`: mock exception scenarios
- [ ] Dùng `@WebMvcTest` cho controller test:
  - Mock service
  - `MockMvc` perform request → assert status, JSON response
- [ ] Coverage target: 80% line coverage

**Output:** `projects/day27-testing-unit/`

---

### 🌅 Ngày 28: Integration Testing & Testcontainers

**Lý thuyết (1h):**
- [ ] `@SpringBootTest` vs `@DataJpaTest` vs `@WebMvcTest`
- [ ] Testcontainers — run real DB (PostgreSQL, MySQL) trong Docker container
- [ ] `@DynamicPropertySource` — override Spring config từ container
- [ ] Test isolation: mỗi test method → clean database state

**Thực hành (2.5h):**
- [ ] Mở rộng `day27-testing-unit` → `day28-testcontainers`
- [ ] Thêm dependency: `testcontainers`, `postgresql`, `junit-jupiter`
- [ ] Viết base test class với `@Container` PostgreSQL
- [ ] `@DataJpaTest`:
  - Test repository methods với real DB
  - Verify query generated (Hibernate show_sql)
- [ ] `@SpringBootTest` với `@Testcontainers`:
  - Integration test cho full flow: create order → process payment → verify DB state
- [ ] Performance: so sánh thời gian chạy test với H2 (in-memory) vs Testcontainers (real PostgreSQL)
- [ ] Viết test: 2 parallel test methods → each gets own DB instance (test isolation)

**Output:** `projects/day28-testcontainers/`

---

## 📅 TUẦN 5: Observability + DevOps + Capstone

---

### 🌅 Ngày 29: Actuator & Micrometer

**Lý thuyết (1h):**
- [ ] Spring Boot Actuator: built-in endpoints
- [ ] Micrometer: vendor-neutral metrics facade
- [ ] Custom metrics: Counter, Gauge, Timer, DistributionSummary
- [ ] Metrics tags: `uri`, `status`, `method` — để filter

**Thực hành (2.5h):**
- [ ] Tạo project `day29-actuator`
- [ ] Cấu hình Actuator:
  - Expose: `health`, `metrics`, `info`, `prometheus`
  - Custom health indicator: `DatabaseHealthIndicator` check connection pool
- [ ] Custom metrics:
  - `order.counter` — số orders created
  - `order.amount.timer` — thời gian xử lý order
  - `cache.hit.rate` — cache hit ratio
- [ ] API endpoint: `GET /api/orders` → increment counter + record timer
- [ ] Viết test: call API 10 lần → verify counter = 10
- [ ] Docker: chạy Prometheus + Grafana để visualize metrics

**Output:** `projects/day29-actuator/`

---

### 🌅 Ngày 30: Docker & Full-stack Capstone

**Lý thuyết (1h):**
- [ ] Multi-stage Dockerfile: build JAR → copy to minimal JRE image
- [ ] Docker Compose: app + PostgreSQL + Redis + RabbitMQ
- [ ] Environment variables: externalize config
- [ ] Health check in Docker: `HEALTHCHECK` instruction

**Thực hành (3h):**
- [ ] **Capstone Project** — xây dựng `order-service` hoàn chỉnh:
  - Entities: `User`, `Product`, `Order`, `OrderItem`
  - JWT authentication (từ day23-24)
  - CRUD với JPA + Redis cache (từ day15-16)
  - Order event → RabbitMQ notification (từ day19-20)
  - Order event → Kafka analytics (từ day21-22)
  - Async notification (từ day25)
  - Unit + Integration tests (từ day27-28)
  - Actuator + custom metrics (từ day29)

- [ ] **Dockerize:**
  - `Dockerfile` multi-stage
  - `docker-compose.yml`: app + PostgreSQL + Redis + RabbitMQ + Kafka
  - Health check cho app

- [ ] **README.md:**
  - Mô tả project, architecture diagram (ASCII)
  - API documentation
  - Docker commands
  - Test commands

- [ ] **Final check:** Build → run docker-compose → test all endpoints → verify metrics → stop

**Output:** `projects/capstone-order-service/`

---

## 🎯 Checklist tổng kết cuối lộ trình

```
□ Ngày 1  □ Bean lifecycle + scopes
□ Ngày 2  □ Auto-configuration
□ Ngày 3  □ DI patterns (field, setter, constructor)
□ Ngày 4  □ @Qualifier, @Primary, custom annotation
□ Ngày 5  □ FactoryBean, ImportBeanDefinitionRegistrar
□ Ngày 6  □ @Transactional proxy, rollback
□ Ngày 7  □ Transaction propagation levels
□ Ngày 8  □ Isolation levels, optimistic/pessimistic locking
□ Ngày 9  □ Entity lifecycle states, cascade
□ Ngày 10 □ N+1 problem + @BatchSize + @EntityGraph
□ Ngày 11 □ JPA Projection + MapStruct
□ Ngày 12 □ JPA Specification pattern
□ Ngày 13 □ Paging/Sorting + Keyset pagination
□ Ngày 14 □ Auditing + Soft deletes
□ Ngày 15 □ Redis data types + configuration
□ Ngày 16 □ Cache-aside pattern + distributed lock
□ Ngày 17 □ Redis: session, lock, rate limiter
□ Ngày 18 □ Redis Pub/Sub + Streams
□ Ngày 19 □ RabbitMQ: exchange, queue, binding, DLQ
□ Ngày 20 □ RabbitMQ: retry, DLQ, idempotency
□ Ngày 21 □ Kafka: producer, consumer, consumer group
□ Ngày 22 □ Kafka: transaction, exactly-once, error handling
□ Ngày 23 □ Spring Security filter chain
□ Ngày 24 □ JWT: access token, refresh token, blacklist
□ Ngày 25 □ @Async + CompletableFuture
□ Ngày 26 □ @Scheduled + Retry + Circuit Breaker
□ Ngày 27 □ Unit test + MockMvc
□ Ngày 28 □ Testcontainers integration test
□ Ngày 29 □ Actuator + Micrometer custom metrics
□ Ngày 30 □ Docker + Capstone project hoàn chỉnh
□        □ README + Deploy lên GitHub
```

---

## 📚 Tài nguyên gợi ý

| Chủ đề | Tài liệu |
|--------|----------|
| Spring Boot internals | `spring.io/projects/spring-boot` — reference docs |
| JPA / Hibernate | Baeldung — "JPA and Hibernate" series |
| Redis | `redis.io/docs` + Baeldung "Spring Data Redis" |
| RabbitMQ | `rabbitmq.com/tutorials` + Baeldung "Spring AMQP" |
| Kafka | Confluent "Kafka 101" + Baeldung "Spring Kafka" |
| Testing | Baeldung "Mockito" + Testcontainers official docs |
| Security | Baeldung "Spring Security" series |
| Docker | Docker official docs + "Dockerizing Spring Boot" |

> **Nguyên tắc:** Mỗi ngày — đọc lý thuyết (30%) + code thực hành (70%). Gặp ngày khó → đánh dấu ⏸️, quay lại cuối tuần. Chất lượng > số lượng. 🚀
