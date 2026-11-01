$target_folder = ".\target"

if (Test-Path $target_folder)
{
    Write-Host "target/dest folder exists. Deleting folder..." -ForegroundColor Green
    Remove-Item -Path $target_folder -Recurse -Force
}

# deletes target directory completely
mvn clean

# maven runs integration and unit tests and makes sure all of them passes
mvn test

# generates dependency lib jars, and converts project into a jar
mvn package

# mvn runs jpackage cmd to generate an exe
mvn jpackage:jpackage -Pexe

# mvn runs jpackage cmd to generate an msi
mvn jpackage:jpackage -Pmsi