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
	;pausing assign
	;starting +0
	%plus0 = alloca i32
	%intermediate0 = alloca i32
	store i32 3, i32* %intermediate0
	%0 = load i32, i32* %intermediate0
	;pausing +0
	;starting +1
	%plus1 = alloca i32
	%intermediate1 = alloca i32
	store i32 2, i32* %intermediate1
	%1 = load i32, i32* %intermediate1	
	%intermediate2 = alloca i32
	store i32 5, i32* %intermediate2
	%2 = load i32, i32* %intermediate2
	%3 = add i32 %1,%2
	;ending +1
	;continuing +0
	%4 = add i32 %0,%3
	;ending +0
	;continuing assign
	store i32 %4, i32* %a
	call void @println(i32 %4)
	ret i32 0
}
