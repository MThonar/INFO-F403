@.strP = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

define void @println(i32 %x) {
  %1 = alloca i32, align 4
  store i32 %x, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %2)
  ret void
}

declare i32 @printf(i8*, ...)

define i32 @main() {
  entry:
  %a = alloca i32
  %b = alloca i32
  store i32 5, i32* %b
  %0 = load i32, i32* %b
  %1 = add i32 %0,2
  store i32 %1, i32* %a
  call void @println(i32 %a)
  ret i32 0
}
