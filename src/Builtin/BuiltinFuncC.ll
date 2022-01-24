; ModuleID = 'BuiltinFuncC.c'
source_filename = "BuiltinFuncC.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__mx_builtin_malloc(i32 %size) #0 {
entry:
  %size.addr = alloca i32, align 4
  store i32 %size, i32* %size.addr, align 4
  %0 = load i32, i32* %size.addr, align 4
  %conv = sext i32 %0 to i64
  %call = call noalias i8* @malloc(i64 %conv) #5
  ret i8* %call
}

; Function Attrs: nounwind
declare dso_local noalias i8* @malloc(i64) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @__mx_builtin_memset(i8* %arr, i32 %val, i32 %size) #0 {
entry:
  %arr.addr = alloca i8*, align 8
  %val.addr = alloca i32, align 4
  %size.addr = alloca i32, align 4
  store i8* %arr, i8** %arr.addr, align 8
  store i32 %val, i32* %val.addr, align 4
  store i32 %size, i32* %size.addr, align 4
  %0 = load i8*, i8** %arr.addr, align 8
  %1 = load i32, i32* %val.addr, align 4
  %2 = trunc i32 %1 to i8
  %3 = load i32, i32* %size.addr, align 4
  %conv = sext i32 %3 to i64
  call void @llvm.memset.p0i8.i64(i8* align 1 %0, i8 %2, i64 %conv, i1 false)
  ret void
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memset.p0i8.i64(i8* nocapture writeonly, i8, i64, i1 immarg) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(i8* %s) #0 {
entry:
  %s.addr = alloca i8*, align 8
  store i8* %s, i8** %s.addr, align 8
  %0 = load i8*, i8** %s.addr, align 8
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %0)
  ret void
}

declare dso_local i32 @printf(i8*, ...) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @println(i8* %s) #0 {
entry:
  %s.addr = alloca i8*, align 8
  store i8* %s, i8** %s.addr, align 8
  %0 = load i8*, i8** %s.addr, align 8
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %0)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printInt(i32 %v) #0 {
entry:
  %v.addr = alloca i32, align 4
  store i32 %v, i32* %v.addr, align 4
  %0 = load i32, i32* %v.addr, align 4
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %0)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printlnInt(i32 %v) #0 {
entry:
  %v.addr = alloca i32, align 4
  store i32 %v, i32* %v.addr, align 4
  %0 = load i32, i32* %v.addr, align 4
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %0)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @getString() #0 {
entry:
  %size = alloca i64, align 8
  %str = alloca i8*, align 8
  %c = alloca i32, align 4
  %len = alloca i64, align 8
  store i64 80, i64* %size, align 8
  %0 = load i64, i64* %size, align 8
  %call = call noalias i8* @malloc(i64 %0) #5
  store i8* %call, i8** %str, align 8
  store i64 0, i64* %len, align 8
  br label %while.cond

while.cond:                                       ; preds = %while.body, %entry
  %call1 = call i32 @getchar()
  store i32 %call1, i32* %c, align 4
  %cmp = icmp ne i32 -1, %call1
  br i1 %cmp, label %land.rhs, label %land.end

land.rhs:                                         ; preds = %while.cond
  %1 = load i32, i32* %c, align 4
  %cmp2 = icmp eq i32 %1, 13
  br i1 %cmp2, label %lor.end, label %lor.lhs.false

lor.lhs.false:                                    ; preds = %land.rhs
  %2 = load i32, i32* %c, align 4
  %cmp3 = icmp eq i32 %2, 10
  br i1 %cmp3, label %lor.end, label %lor.lhs.false4

lor.lhs.false4:                                   ; preds = %lor.lhs.false
  %3 = load i32, i32* %c, align 4
  %cmp5 = icmp eq i32 %3, 32
  br i1 %cmp5, label %lor.end, label %lor.rhs

lor.rhs:                                          ; preds = %lor.lhs.false4
  %4 = load i32, i32* %c, align 4
  %cmp6 = icmp eq i32 %4, 9
  br label %lor.end

lor.end:                                          ; preds = %lor.rhs, %lor.lhs.false4, %lor.lhs.false, %land.rhs
  %5 = phi i1 [ true, %lor.lhs.false4 ], [ true, %lor.lhs.false ], [ true, %land.rhs ], [ %cmp6, %lor.rhs ]
  br label %land.end

land.end:                                         ; preds = %lor.end, %while.cond
  %6 = phi i1 [ false, %while.cond ], [ %5, %lor.end ]
  br i1 %6, label %while.body, label %while.end

while.body:                                       ; preds = %land.end
  br label %while.cond

while.end:                                        ; preds = %land.end
  %7 = load i32, i32* %c, align 4
  %cmp7 = icmp ne i32 %7, -1
  br i1 %cmp7, label %if.then, label %if.end

if.then:                                          ; preds = %while.end
  %8 = load i32, i32* %c, align 4
  %conv = trunc i32 %8 to i8
  %9 = load i8*, i8** %str, align 8
  %10 = load i64, i64* %len, align 8
  %inc = add i64 %10, 1
  store i64 %inc, i64* %len, align 8
  %arrayidx = getelementptr inbounds i8, i8* %9, i64 %10
  store i8 %conv, i8* %arrayidx, align 1
  br label %if.end

if.end:                                           ; preds = %if.then, %while.end
  br label %while.cond8

while.cond8:                                      ; preds = %if.end26, %if.end
  %call9 = call i32 @getchar()
  store i32 %call9, i32* %c, align 4
  %cmp10 = icmp ne i32 -1, %call9
  br i1 %cmp10, label %land.lhs.true, label %land.end17

land.lhs.true:                                    ; preds = %while.cond8
  %11 = load i32, i32* %c, align 4
  %cmp12 = icmp ne i32 %11, 13
  br i1 %cmp12, label %land.rhs14, label %land.end17

land.rhs14:                                       ; preds = %land.lhs.true
  %12 = load i32, i32* %c, align 4
  %cmp15 = icmp ne i32 %12, 10
  br label %land.end17

land.end17:                                       ; preds = %land.rhs14, %land.lhs.true, %while.cond8
  %13 = phi i1 [ false, %land.lhs.true ], [ false, %while.cond8 ], [ %cmp15, %land.rhs14 ]
  br i1 %13, label %while.body18, label %while.end27

while.body18:                                     ; preds = %land.end17
  %14 = load i32, i32* %c, align 4
  %conv19 = trunc i32 %14 to i8
  %15 = load i8*, i8** %str, align 8
  %16 = load i64, i64* %len, align 8
  %inc20 = add i64 %16, 1
  store i64 %inc20, i64* %len, align 8
  %arrayidx21 = getelementptr inbounds i8, i8* %15, i64 %16
  store i8 %conv19, i8* %arrayidx21, align 1
  %17 = load i64, i64* %len, align 8
  %18 = load i64, i64* %size, align 8
  %cmp22 = icmp eq i64 %17, %18
  br i1 %cmp22, label %if.then24, label %if.end26

if.then24:                                        ; preds = %while.body18
  %19 = load i8*, i8** %str, align 8
  %20 = load i64, i64* %size, align 8
  %mul = mul i64 %20, 2
  store i64 %mul, i64* %size, align 8
  %call25 = call i8* @realloc(i8* %19, i64 %mul) #5
  store i8* %call25, i8** %str, align 8
  br label %if.end26

if.end26:                                         ; preds = %if.then24, %while.body18
  br label %while.cond8

while.end27:                                      ; preds = %land.end17
  %21 = load i8*, i8** %str, align 8
  %22 = load i64, i64* %len, align 8
  %inc28 = add i64 %22, 1
  store i64 %inc28, i64* %len, align 8
  %arrayidx29 = getelementptr inbounds i8, i8* %21, i64 %22
  store i8 0, i8* %arrayidx29, align 1
  %23 = load i8*, i8** %str, align 8
  %24 = load i64, i64* %len, align 8
  %call30 = call i8* @realloc(i8* %23, i64 %24) #5
  ret i8* %call30
}

declare dso_local i32 @getchar() #3

; Function Attrs: nounwind
declare dso_local i8* @realloc(i8*, i64) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt() #0 {
entry:
  %v = alloca i32, align 4
  %call = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %v)
  %0 = load i32, i32* %v, align 4
  ret i32 %0
}

declare dso_local i32 @__isoc99_scanf(i8*, ...) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @toString(i32 %v) #0 {
entry:
  %v.addr = alloca i32, align 4
  %res = alloca i8*, align 8
  store i32 %v, i32* %v.addr, align 4
  %call = call noalias i8* @malloc(i64 200) #5
  store i8* %call, i8** %res, align 8
  %0 = load i8*, i8** %res, align 8
  %1 = load i32, i32* %v.addr, align 4
  %call1 = call i32 (i8*, i8*, ...) @sprintf(i8* %0, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %1) #5
  %2 = load i8*, i8** %res, align 8
  ret i8* %2
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @__mx_builtin_strcmp(i8* %a, i8* %b) #0 {
entry:
  %a.addr = alloca i8*, align 8
  %b.addr = alloca i8*, align 8
  store i8* %a, i8** %a.addr, align 8
  store i8* %b, i8** %b.addr, align 8
  %0 = load i8*, i8** %a.addr, align 8
  %1 = load i8*, i8** %b.addr, align 8
  %call = call i32 @strcmp(i8* %0, i8* %1) #6
  %tobool = icmp ne i32 %call, 0
  ret i1 %tobool
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strcmp(i8*, i8*) #4

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__mx_builtin_strcat(i8* %a, i8* %b) #0 {
entry:
  %a.addr = alloca i8*, align 8
  %b.addr = alloca i8*, align 8
  %res = alloca i8*, align 8
  store i8* %a, i8** %a.addr, align 8
  store i8* %b, i8** %b.addr, align 8
  %0 = load i8*, i8** %a.addr, align 8
  %call = call i64 @strlen(i8* %0) #6
  %1 = load i8*, i8** %b.addr, align 8
  %call1 = call i64 @strlen(i8* %1) #6
  %add = add i64 %call, %call1
  %add2 = add i64 %add, 1
  %call3 = call noalias i8* @malloc(i64 %add2) #5
  store i8* %call3, i8** %res, align 8
  %2 = load i8*, i8** %res, align 8
  %3 = load i8*, i8** %a.addr, align 8
  %call4 = call i8* @strcpy(i8* %2, i8* %3) #5
  %4 = load i8*, i8** %res, align 8
  %5 = load i8*, i8** %b.addr, align 8
  %call5 = call i8* @strcat(i8* %4, i8* %5) #5
  %6 = load i8*, i8** %res, align 8
  ret i8* %6
}

; Function Attrs: nounwind readonly
declare dso_local i64 @strlen(i8*) #4

; Function Attrs: nounwind
declare dso_local i8* @strcpy(i8*, i8*) #1

; Function Attrs: nounwind
declare dso_local i8* @strcat(i8*, i8*) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_str_length(i8* %a) #0 {
entry:
  %a.addr = alloca i8*, align 8
  store i8* %a, i8** %a.addr, align 8
  %0 = load i8*, i8** %a.addr, align 8
  %call = call i64 @strlen(i8* %0) #6
  %conv = trunc i64 %call to i32
  ret i32 %conv
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__mx_str_substring(i8* %a, i32 %l, i32 %r) #0 {
entry:
  %a.addr = alloca i8*, align 8
  %l.addr = alloca i32, align 4
  %r.addr = alloca i32, align 4
  %res = alloca i8*, align 8
  store i8* %a, i8** %a.addr, align 8
  store i32 %l, i32* %l.addr, align 4
  store i32 %r, i32* %r.addr, align 4
  %0 = load i32, i32* %r.addr, align 4
  %1 = load i32, i32* %l.addr, align 4
  %sub = sub nsw i32 %0, %1
  %add = add nsw i32 %sub, 1
  %conv = sext i32 %add to i64
  %call = call noalias i8* @malloc(i64 %conv) #5
  store i8* %call, i8** %res, align 8
  %2 = load i8*, i8** %res, align 8
  %3 = load i8*, i8** %a.addr, align 8
  %4 = load i32, i32* %l.addr, align 4
  %idx.ext = sext i32 %4 to i64
  %add.ptr = getelementptr inbounds i8, i8* %3, i64 %idx.ext
  %5 = load i32, i32* %r.addr, align 4
  %6 = load i32, i32* %l.addr, align 4
  %sub1 = sub nsw i32 %5, %6
  %conv2 = sext i32 %sub1 to i64
  %call3 = call i8* @strncpy(i8* %2, i8* %add.ptr, i64 %conv2) #5
  %7 = load i8*, i8** %res, align 8
  ret i8* %7
}

; Function Attrs: nounwind
declare dso_local i8* @strncpy(i8*, i8*, i64) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_str_parseInt(i8* %s) #0 {
entry:
  %s.addr = alloca i8*, align 8
  store i8* %s, i8** %s.addr, align 8
  %0 = load i8*, i8** %s.addr, align 8
  %call = call i32 @atoi(i8* %0) #6
  ret i32 %call
}

; Function Attrs: nounwind readonly
declare dso_local i32 @atoi(i8*) #4

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_str_ord(i8* %s, i32 %idx) #0 {
entry:
  %s.addr = alloca i8*, align 8
  %idx.addr = alloca i32, align 4
  store i8* %s, i8** %s.addr, align 8
  store i32 %idx, i32* %idx.addr, align 4
  %0 = load i8*, i8** %s.addr, align 8
  %1 = load i32, i32* %idx.addr, align 4
  %idxprom = sext i32 %1 to i64
  %arrayidx = getelementptr inbounds i8, i8* %0, i64 %idxprom
  %2 = load i8, i8* %arrayidx, align 1
  %conv = sext i8 %2 to i32
  ret i32 %conv
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { argmemonly nounwind willreturn }
attributes #3 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { nounwind }
attributes #6 = { nounwind readonly }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
