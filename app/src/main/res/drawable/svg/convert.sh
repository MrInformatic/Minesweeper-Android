
for f in *.svg
do
        echo "$f"
        inkscape -z -e "${f::-4}.png" -w 32 -h 32 "$f" 
done