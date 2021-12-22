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
	;plus0 start
	%plus0 = alloca i32
	%intermediate0 = alloca i32
	store i32 1, i32* %intermediate0
	%0 = load i32, i32* %intermediate0
	;plus1 start
	%plus1 = alloca i32
	%intermediate1 = alloca i32
	store i32 5, i32* %intermediate1
	%1 = load i32, i32* %intermediate1
	;times0 start
	%times0 = alloca i32
	%intermediate2 = alloca i32
	store i32 3, i32* %intermediate2
	%2 = load i32, i32* %intermediate2
	%intermediate3 = alloca i32
	store i32 2, i32* %intermediate3
	%3 = load i32, i32* %intermediate3
	%4 = mul i32 %2,%3
	;times0 end
	%5 = add i32 %1,%4
	;plus1 end
	%6 = add i32 %0,%5
	;plus0 end
	store i32 %6, i32* %a
	call void @println(i32 %6)
	ret i32 0
}
