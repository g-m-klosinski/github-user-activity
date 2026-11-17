# GitHub User Activity

A console application displaying recent activity of a GitHub user.

Created for
[github-user-activity](https://roadmap.sh/projects/github-user-activity)
project on [roadmap.sh](https://roadmap.sh).

## Usage

Pass the username as an argument, for example:
```bash
./mvnw exec:java -Dexec.args="g-m-klosinski"
```

The application will print the activity.

## Roadmap

The application is already functional and has
the following roadmap:

- [x] Consider push events only
- [x] Count push events per week
- [ ] Refactor long printing method
- [ ] Test printing
