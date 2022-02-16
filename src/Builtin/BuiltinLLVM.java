package Builtin;

import IR.Type.FuncType;
import IR.Type.IntType;
import IR.Type.PointerType;
import IR.Type.Type;
import IR.Value.Global.Function;

import java.util.Arrays;
import java.util.List;

// builtin global function:
// void print(string str);
// void println(string str);
// void printInt(int n);
// void printlnInt(int n);
// string getString();
// int getInt();
// string toString(int i);

// i8* @__mx_builtin_malloc(i32)
// void @__mx_builtin_memset(i8*, i32, i32)
// i1 @__mx_builtin_strcmp(i8*, i8*)
// i8* @__mx_builtin_strcat(i8*, i8*)

// i32 @__mx_str_length(i8*)
// i8* @__mx_str_substring(i8*, i32, i32)
// i32 @__mx_str_parseInt(i8*)
// i32 @__mx_str_ord(i8*, i32)

public final class BuiltinLLVM {
	private static final Type VOID = Type.VOID;
	private static final Type INT1 = IntType.INT1;
	private static final Type INT32 = IntType.INT32;
	private static final Type I8STAR = PointerType.I8STAR;
	public static List<Function> builtinFunc = Arrays.asList(
			new Function(new FuncType(VOID, I8STAR), "print"),
			new Function(new FuncType(VOID, I8STAR), "println"),
			new Function(new FuncType(VOID, INT32), "printInt"),
			new Function(new FuncType(VOID, INT32), "printlnInt"),
			new Function(new FuncType(I8STAR), "getString"),
			new Function(new FuncType(INT32), "getInt"),
			new Function(new FuncType(I8STAR, INT32), "toString"),
			new Function(new FuncType(I8STAR, INT32), "__mx_builtin_malloc"),
			new Function(new FuncType(VOID, I8STAR, INT32, INT32), "__mx_builtin_memset"),
			new Function(new FuncType(INT32, I8STAR, I8STAR), "__mx_builtin_strcmp"),
			new Function(new FuncType(I8STAR, I8STAR, I8STAR), "__mx_builtin_strcat"),
			new Function(new FuncType(INT32, I8STAR), "__mx_str_length"),
			new Function(new FuncType(I8STAR, I8STAR, INT32, INT32), "__mx_str_substring"),
			new Function(new FuncType(INT32, I8STAR), "__mx_str_parseInt"),
			new Function(new FuncType(INT32, I8STAR, INT32), "__mx_str_ord")
	);
}
