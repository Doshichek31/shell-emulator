@echo off

set SRC_DIR=src
set OUT_DIR=out
set MAIN_CLASS=com.vfs.ShellEmulator

if not exist "%SRC_DIR%" (
    echo ERROR: Source directory '%SRC_DIR%' not found
    exit /b 1
)

if not exist "%OUT_DIR%" (
    mkdir "%OUT_DIR%"
)

echo Compiling Java files...

for /r "%SRC_DIR%" %%f in (*.java) do (
    javac -d "%OUT_DIR%" "%%f"
)

if errorlevel 1 (
    echo ERROR: Compilation failed
    exit /b 1
)

echo Compilation successful!

echo Running application...
java -cp "%OUT_DIR%" %MAIN_CLASS%

pause