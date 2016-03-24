while (( "$#" )); do
    java -jar iZiPatch.jar $1
    shift
done