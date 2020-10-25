project_dir="$1"
exe_name="$2"
user="$3"
id_path="$4"

rm -r "$project_dir"

mkdir "$project_dir"

# Naively copy in the required files.


cp ./resources/Makefile "$project_dir"
cp ./resources/client "$project_dir"
cp -r ./resources/"$project_dir"/* "$project_dir"/
cp -r ../src "$project_dir"

# Rename the executable to the specified name
mv "$project_dir"/client "$project_dir"/"$exe_name"

# Fix the line endings on all the files
find ./"$project_dir" -type f -execdir sed -i 's/\r//' {} \;

# Put the JAR in last to avoid mucking with it when fixing the line endings
cp ../target/server-0.0.1-SNAPSHOT.jar "$project_dir"

# Send the prepared submission directory to the remote server
scp -r "$project_dir" "$user"@login.ccs.neu.edu:cs3700

# Remove the local copy of the submission directory
rm -r "$project_dir"
