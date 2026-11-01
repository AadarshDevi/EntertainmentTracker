$target_folder = ".\target"

if (Test-Path $target_folder)
{
    Write-Host "target/dest folder exists. Deleting folder..." -ForegroundColor Green
    Remove-Item -Path $target_folder -Recurse -Force
}