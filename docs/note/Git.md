<h2 align="center">📔 Git</h2>

### 🔒 设置

#### 网络代理

* 添加网络代理

```bash
git config --global http.proxy http://127.0.0.1:10809
```

```bash
git config --global https.proxy http://127.0.0.1:10809
```

* 还原网络代理

```bash
git config --global --unset http.proxy
```

```bash
git config --global --unset https.proxy
```

---

### 🔑 命令

#### 远程仓库 `remote`

* 添加远程仓库

```bash
git remote add github https://github.com/zhouboyi1998/mall-cloud.git
```

* 查看关联的远程仓库

```bash
git remote show
```

```bash
git remote -v
```

---

#### 回退 `reset`

* 回退到上一个版本

```bash
git reset --soft HEAD^
```

* 回退到指定版本

```bash
git reset --soft bcd58534
```

* 参数

```
--soft 本地仓库回退，改动迁移到暂存区
--mixed 本地仓库、暂存区回退，改动迁移到工作区
--hard 本地仓库、暂存区、工作区回退（改动可以通过 reflog 操作日志找回）
```

---

#### 变基 `rebase`

* 从指定版本开始变基

```bash
git rebase -i bcd58534
```

* `i` 进入编辑模式，设置每一个版本的改动类型
    * `pick (p)`：保留该版本
    * `reword (r)`：保留该版本，修改该版本的备注
    * `edit (e)`：保留该版本，修改该版本的内容、备注
    * `squash (s)`：将该版本的内容、备注合并到上一个版本中
    * `fixup (f)`：将该版本的内容合并到上一个版本中，丢弃该版本的备注
    * `drop (d)`：丢弃该版本
    * `exec (x)`：执行 `shell` 命令
* `esc` 退出编辑模式
* `:wq` 保存设置


* 然后对所有需要改动的版本进行相应的操作

###### fixup

* 对需要 `fixup` 的版本进行以下操作
* 如果遇到无法合并的文件，手动解决冲突，合并代码，重新进入变基

###### reword、squash

* 对需要 `reword`、`squash` 的版本进行以下操作
* `i` 进入编辑模式
* 修改该版本的备注
* `esc` 退出编辑模式
* `:wq` 保存修改

* 如果需要 `squash` 的版本遇到无法合并的文件，手动解决冲突，合并代码，重新进入变基
* 重新进入变基之前，可以先执行一次 `add` 操作

```bash
git add .
```

```bash
git rebase --continue
```

###### edit

* 对需要 `edit` 的版本进行以下操作
* 打开代码编辑器，此时该版本的内容是上一次提交时的状态
* 修改该版本的内容
* 执行 `add` 操作

```bash
git add .
```

* 执行修订提交操作

```bash
git commit --amend
```

* 重新进入变基

```bash
git rebase --continue
```

---

#### 推送 `push`

* 强制推送

```bash
git push origin master --force
```

```bash
git push github master --force
```
