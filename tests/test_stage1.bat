@echo off

echo === VFS Shell Emulator - Stage 1 Tests ===
echo.

echo Compiling...
for /r src %%f in (*.java) do (
    javac -d out "%%f"
)

if errorlevel 1 (
    echo Compilation failed
    exit /b 1
)

echo Compilation successful!
echo.

echo Running automated tests...
echo.

echo ls > test_input.txt
echo ls -la /home >> test_input.txt
echo cd /home/user >> test_input.txt
echo cd >> test_input.txt
echo exit >> test_input.txt

type test_input.txt | java -cp out com.vfs.ShellEmulator

echo.
echo Tests completed!

del test_input.txt

pause