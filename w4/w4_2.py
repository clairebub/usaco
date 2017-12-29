# read from file mnoteb.in
# write to file mnoteb.out
import sys

# read the input and build a data structures to record the start and end time
# of the music notes, [start, end) means the given notes start time is inclusive
# while the end time is not inclusive
fin = open("mnoteb.in", "r")
num_notes, num_q = fin.readline().strip().split()
num_notes, num_q = int(num_notes), int(num_q)

notes = []
start = 0
end = 0
for i in range(num_notes):
    beats = fin.readline().strip()
    beats = int(beats)
    end = start + beats
    notes.append([start, end])
    start = end
    end = None
print("notes", notes)

# now we are going to use the 'notes' we build to answer questions
fout = open("mnoteb.out", "w") # the file to write answers to
for i in range(num_q):
    beat = fin.readline().strip()
    beat = int(beat)
    for note_index in range(len(notes)):
        note = notes[note_index]
        if beat >= note[0] and beat < note[1]:
            print(note_index+1, file=fout) # it expected the first note to be 1 instead of 0
            break
