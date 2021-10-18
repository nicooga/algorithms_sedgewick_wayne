> 1.5.10 In the weighted quick-union algorithm, suppose that we set `id[find(p)]` to
> instead of to `id[find(q)]`. Would the resulting algorithm be correct?
> Answer : Yes, but it would increase the tree height, so the performance guarantee would
> be invalid

We would we doing the opposite of normal: attaching the larger tree to the smaller one.
This makes it so we always generate a larger tree each time we merge.