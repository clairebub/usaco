import sys
fin = sys.stdin

# read in the number of tests
num_tests = fin.readline().strip()
num_tests = int(num_tests)
# get the individual test scores in to a list
test_score_fields = fin.readline().strip().split()
test_scores = []
for t in test_score_fields:
  test_scores.append(int(t))
# well, we don't really need num_tests, because we can get it
# as len(test_scores). However, as a pro, we sometimes do a check
# on:
# if len(test_scores) == num_tests
# just to verify we read the input correctly
bonus = fin.readline().strip()
bonus = int(bonus)
# read in the number of submissions
num_submissions = fin.readline().strip()
num_submissions = int(num_submissions)
# first submission has no penalty, but each subsequent one
# will be assessed 2 penalty points
penalty = 0
best_score = None
# assess scores of each submissions
for i in range(num_submissions):
  results = []
  for field in fin.readline().strip().split():
    results.append(int(field))
  all_correct = True
  score = 0
  for i in range(len(results)):
    if results[i] == 1:
      score += test_scores[i]
    else:
      all_correct = False
  if all_correct:
    score += bonus
  score -= penalty
  if best_score is None or score > best_score:
    best_score = score
  # penalty increase by two for the next round
  penalty += 2
print(best_score)
