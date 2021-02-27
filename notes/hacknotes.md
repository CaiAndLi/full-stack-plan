# 黑客笔记

## 文件名以横线开头的情况

横线开头通常会被识别为参数名，如果是单独的横线意思是stdin/stdout，所以有以下解决办法：

```shell
cat < -filename
cat ./-filename
cat -- -filename # --表示告诉shell后面没有参数名了
```

## 查看文件类型

```shell
file "filename"
file -- *
```

文本文档描述里会有text，比如“ASCII text”。筛选文本文档：`file -- * | grep text`

## 查找文件

`find [文件路径] [筛选条件] [-exec 对文件执行的命令 {} \;]`

筛选条件示例：

```shell
-name "*.txt" # 筛选文件名，接受正则表达式
-iname "filename" # 不区分大小写
-type f # 筛选文件类型，f表示文件，d表示文件夹，l表示软链接
-size 100c # 文件大小为100字节的
-size +10M # 文件大小超过10M的
-size +20k -size -1G # 文件大小大于20k小于1G的
-user caichunyu
-group root
-mtime -2 # 按更新时间筛选，单位天，正负号代表大于小于
-mmin +10 # 按更新时间筛选，单位分钟，正负号代表大于小于
-executable
```

筛选条件前面加英文叹号表示not

```shell
find . ! -type d ! -executable
```

对文件执行命令：

```shell
find . -type d -exec ls -al {} \;
find . -type f ! -executable -size 1033c -exec file {} \; | grep text
```

## 给文件内容排序

```shell
sort data.txt
```

## 给文件内容去重

```shell
sort data.txt | uniq
sort data.txt | uniq -u # 只保留没有重复的
sort data.txt | uniq -d # 只保留重复的
sort data.txt | uniq -c # 统计重复次数
```

## 列出文件中所有的ascii文本

```shell
strings 
```

## 替换文件中的字符
tr从stdin获取输入，输出到stdout

```shell
cat testfile | tr a-z A-Z # 小写转大写
cat testfile | tr '{}' '()' # 所有大括号转为小括号
cat testfile | tr [:space:] '\t' # 把每个空格都替换为制表符
cat testfile | tr -d 'w' # 删除所有w
cat testfile | tr -s [:space:] ' ' # 把所有连续的空格都替换为一个空格
cat testfile | tr -d [:digit:] # 删除所有的数字
echo "my ID is 73535" | tr -cd [:digit:] # 删除数字以外的所有字符
cat data.txt | tr '[a-z][A-Z]' '[n-za-m][N-ZA-M]' # 旋转13位
```

[:alnum:] ：所有字母字符与数字
[:alpha:] ：所有字母字符
[:blank:] ：所有水平空格
[:cntrl:] ：所有控制字符
[:digit:] ：所有数字
[:graph:] ：所有可打印的字符(不包含空格符)
[:lower:] ：所有小写字母
[:print:] ：所有可打印的字符(包含空格符)
[:punct:] ：所有标点字符
[:space:] ：所有水平与垂直空格符
[:upper:] ：所有大写字母
[:xdigit:] ：所有 16 进位制的数字

## base64

```shell
cat filename | base64 # encode
cat filename | base64 -d # decode
```

## 二进制文件查看和修改

```shell
xxd filename output.txt
xxd -r output.txt
```

## 用私钥登录

```shell
ssh username@localhost -i privatekey.txt
scp -i privatekey username@localhost:~/data.txt .
```

## 向某个端口发送内容

```shell
echo 4wcYUJFw0k0XLShlDzztnTBHiqxU3b3e | nc localhost 30000
echo BfMYroe26WYalil77FoDi9qh59eK5xNr | openssl s_client -connect localhost:30001 -ign_eof
```

## 监听某个端口

```shell
nc -l -p 31111
```

## 扫描端口

```shell
nmap localhost -p31000-32000
nmap -A -T4 localhost -p31000-32000 # A表示全部信息，T4是加速
```

## 比较文件不同

```shell
diff file1 file2
```

## 远程执行命令

```shell
ssh bandit18@bandit.labs.overthewire.org -p 2220 "ls -al; cat readme"
```

## 分屏

1，输入命令tmux使用工具
2，上下分屏：ctrl + b  再按 "
3，左右分屏：ctrl + b  再按 %
4，切换屏幕：ctrl + b  再按o或者;
5，关闭一个终端：ctrl + b  再按x
6，上下分屏与左右分屏切换： ctrl + b  再按空格键

## 打印数字前面补0

```shell
printf "hello %04d" 1
```

## 有超时时间地执行程序

```shell
timeout -s 9 60 ./helloworld # 60秒后kill -9
```

## 循环

```shell
for i in * .*;
do
    # somthing
done

for (( i=0; i<=10; i++ ))
do
    # something
done
```

## if

```shell
if [ "$i" != "." -a "$i" != ".." ];
then
    # something
fi
```

## 利用more

more的内容尺寸大于窗口时可以进行操作，按v进入vi模式

## 利用vi

```shell
:e 文件路径
:set shell=/bin/bash
:!ls -al
```

## git

```shell
git log
git reset --hard HEAD^
git branch -a
git checkout -b dev origin/dev
git tag
```

## 只用标点符号进入bash

```shell
$0
${0/-/}
```
