@.strR = private unnamed_addr constant [3 x i8] c"%d\00", align 1

define i32 @readInt() {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32 0, i32 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

declare i32 @scanf(i8*, ...)

@.strP = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

define void @println(i32 %x) {
  %1 = alloca i32, align 4
  store i32 %x, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %2)
  ret void
}

declare i32 @printf(i8*, ...)

define i32 @main(){
%a = alloca i32
%plus1 = alloca i32
%intermediate1 = alloca i32
store i32 3, i32* %intermediate1
%1 = load i32, i32* %intermediate1
%plus2 = alloca i32
%intermediate2 = alloca i32
store i32 2, i32* %intermediate2
%2 = load i32, i32* %intermediate2
%intermediate3 = alloca i32
store i32 5, i32* %intermediate3
%3 = load i32, i32* %intermediate3
%4 = add i32 %2,%3
%5 = add i32 %1,%4
store i32 %5, i32* %a
ret i32 0
}
