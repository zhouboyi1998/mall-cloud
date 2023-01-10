<h2 align="center">📔 MySQL</h2>

### 📝 SQL 规范

1. 禁止使用 `SELECT *`，需要哪些字段必须明确指出
    * 增加查询分析器成本
    * 增减字段容易与 `resultMap` 配置不一致
    * 无用字段增加网络消耗，尤其是 `text` 类型的字段


2. 避免使用 `IS NULL` / `IS NOT NULL` 关键字
    * 如果需要判空：使用 `ISNULL()` / `!ISNULL()` 函数


3. 禁止使用 `NULL` 关键字来判断是否等于 `NULL` 值
    * 如果需要判断是否等于 `NULL` 值：使用 `ISNULL()` 函数
    * `NULL = NULL` 的返回结果是 `NULL` 而不是 `true`
    * `NULL <> NULL` 的返回结果是 `NULL` 而不是 `false`
    * `NULL <> 1` 的返回结果是 `NULL` 而不是 `true`


4. 禁止使用级联更新和外键
    * 级联更新是强阻塞，存在数据库更新风暴的风险
    * 如果使用外键：
        * `tb_student` 中的 `student_id` 是主键
        * `tb_score` 中的 `student_id` 是外键
        * 如果更新 `tb_student` 中的 `student_id`
        * 会同时触发 `tb_score` 中的 `student_id` 更新
        * 即触发了级联更新
    * 外键影响数据库的插入速度


5. 禁止使用 `COUNT(列名)` 代替 `COUNT(*)`
    * `COUNT(列名)` 不会统计该列中为空的行
