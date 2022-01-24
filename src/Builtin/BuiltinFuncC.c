
// C implementation of Mx built-in functions
// may lead to memory leaking because Mx does not have memory management

// builtin global function:
// void print(string str);
// void println(string str);
// void printInt(int n);
// void printlnInt(int n);
// string getString();
// int getInt();
// string toString(int i);

// i8* __mx_builtin_malloc(i32)
// void __mx_builtin_memset(i8*, i32, i32)
// i1 @__mx_builtin_strcmp(i8*, i8*)
// i8* @__mx_builtin_strcat(i8*, i8*)

// i32 @__mx_str_length(i8*)
// i8* @__mx_str_substring(i8*, i32, i32)
// i32 @__mx_str_parseInt(i8*)
// i32 @__mx_str_ord(i8*, i32)

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

char* __mx_builtin_malloc(int size) {
	return malloc(size);
}
void __mx_builtin_memset(char *arr, int val, int size) {
	memset(arr, val, size);
}

void print(char *s) {
	printf("%s", s);
}
void println(char *s) {
	printf("%s\n", s);
}
void printInt(int v) {
	printf("%d", v);
}
void printlnInt(int v) {
	printf("%d\n", v);
}

// get string of any size
char *getString() {
  size_t size = 80;
  char *str = malloc(size);
  int c;
  size_t len = 0;
  while (EOF != (c = getchar()) && (c == '\r' || c == '\n' || c == ' ' || c == '\t'));
  if (c != EOF) str[len++] = c;
  while (EOF != (c = getchar()) && c != '\r' && c != '\n') {
    str[len++] = c;
    if(len == size) str = realloc(str, (size *= 2));
  }
  str[len++]='\0';
  return realloc(str, len);
}

int getInt() {
	int v;
	scanf("%d", &v);
	return v;
}

char *toString(int v) {
	char *res = malloc(200);
	sprintf(res, "%d", v);
	return res;
}

_Bool __mx_builtin_strcmp(char *a, char *b) {
	return strcmp(a, b);
}

char* __mx_builtin_strcat(char *a, char *b) {
	char *res = malloc(strlen(a) + strlen(b) + 1);
	strcpy(res, a); // safe, no pointer out of range
	strcat(res, b);
	return res;
}

int __mx_str_length(char *a) {
	return strlen(a);
}

char* __mx_str_substring(char *a, int l, int r) {
	char *res = malloc(r - l + 1);
	strncpy(res, a + l, r - l);
	return res;
}

int __mx_str_parseInt(char *s) {
	return atoi(s);
}

int __mx_str_ord(char *s, int idx) {
	return (int)(s[idx]);
}
