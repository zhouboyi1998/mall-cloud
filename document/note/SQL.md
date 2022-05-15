<h2 align="center">📔 SQL</h2>

### 📝 规范

* 禁止使用 `SELECT *`，需要哪些字段必须明确指出
    1. 增加查询分析器成本
    2. 增减字段容易与 `resultMap` 配置不一致
    3. 无用字段增加网络消耗，尤其是 `text` 类型的字段

---

* 避免使用 `IS NULL` / `IS NOT NULL` 关键字
* 如果需要判空：使用 `ISNULL()` / `!ISNULL()` 函数
  

* 禁止使用 `NULL` 关键字来判断是否等于 `NULL` 值
* 如果需要判断是否等于 `NULL` 值：使用 `ISNULL()` 函数
  1. `NULL = NULL` 的返回结果是 `NULL` 而不是 `false`
  2. `NULL <> NULL` 的返回结果是 `NULL` 而不是 `true`
  3. `NULL <> 1` 的返回结果是 `NULL` 而不是 `true`

---

* 禁止使用级联和外键
  1. 级联更新是强阻塞，存在数据库更新风暴的风险
  2. 如果使用外键：
      * `tb_student` 中的 `student_id` 是主键
      * `tb_score` 中的 `student_id` 是外键
      * 如果更新 `tb_student` 中的 `student_id`
      * 会同时触发 `tb_score` 中的 `student_id` 更新
      * 即触发了级联更新
  3. 外键影响数据库的插入速度

---

* 禁止使用 `COUNT(列名)` 代替 `COUNT(*)`
* `COUNT(列名)` 不会统计该列中为空的行

---
